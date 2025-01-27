package live.denisdev.concerti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class Concerti {

    private ArrayList<Concerto> concerti = new ArrayList<>();
    public Concerti() {
        createDatabase();
        loadConcertiFromDatabase();
        Logger.log("DB Caricato", false);
    }
    public void addNazionale(String artista, String luogo, String data, Double prezzo) {
        concerti.add(new ConcertoNazionale(artista, luogo, data, prezzo));
    }

    public void addInternazionale(String artista, String luogo, String data, Double prezzo) {
        concerti.add(new ConcertoInternazionale(artista, luogo, data, prezzo));
    }

    public ObservableList<Concerto> getConcerti() {
        return FXCollections.observableArrayList(concerti);
    }
    public ObservableList<Concerto> getConcerti(String luogo) {
        if (luogo.equals("")) {
            return getConcerti();
        }
        ArrayList<Concerto> concertiFiltrati = new ArrayList<>();
        for (Concerto c : concerti) {
            if (c.getLuogo().equals(luogo)) {
                concertiFiltrati.add(c);
            }
        }
        return FXCollections.observableArrayList(concertiFiltrati);
    }

    public void rimConcerto(Concerto index) {
        concerti.remove(index);
    }
    public void modConcerto(String artista, String luogo, String data, Double prezzo, boolean nazionale) {
        concerti.add(nazionale ? new ConcertoNazionale(artista, luogo, data, prezzo) : new ConcertoInternazionale(artista, luogo, data, prezzo));
    }
    private static final String DB_URL = "jdbc:sqlite:concerti.db";
    private static final String sql = "INSERT INTO concerto (artista, luogo, data, prezzo, concertoInternazionale) VALUES (?, ?, ?, ?, ?)";
    public static void insertConcerto(String artista, String luogo, String data, double prezzo, boolean concertoInternazionale) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, artista);
            pstmt.setString(2, luogo);
            pstmt.setString(3, data);
            pstmt.setDouble(4, prezzo);
            pstmt.setBoolean(5, concertoInternazionale);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Logger.log("Concerto inserito: " + artista + " " + luogo + " " + data + " " + prezzo + " " + concertoInternazionale, false);
    }
    private void loadConcertiFromDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT * FROM concerto";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String artista = rs.getString("artista");
                    String luogo = rs.getString("luogo");
                    String data = rs.getString("data");
                    double prezzo = rs.getDouble("prezzo");
                    boolean internazionale = rs.getBoolean("concertoInternazionale");
                    if (internazionale) {
                        concerti.add(new ConcertoInternazionale(artista, luogo, data, prezzo));
                    } else {
                        concerti.add(new ConcertoNazionale(artista, luogo, data, prezzo));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Concerto c : concerti) {
            Logger.log(c.getArtista() + " " + c.getLuogo() + " " + c.getData() + " " + c.getPrezzo(), false);
        }
    }
    public static void deleteConcerto(Concerto concerto) {
        String sql = "DELETE FROM concerto WHERE artista = ? AND luogo = ? AND data = ? AND prezzo = ? AND concertoInternazionale = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, concerto.getArtista());
            pstmt.setString(2, concerto.getLuogo());
            pstmt.setString(3, concerto.getData());
            pstmt.setDouble(4, concerto.getPrezzo());
            pstmt.setBoolean(5, concerto instanceof ConcertoInternazionale);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Logger.log("Concerto eliminato: " + concerto.getArtista() + " " + concerto.getLuogo() + " " + concerto.getData() + " " + concerto.getPrezzo() + " " + (concerto instanceof ConcertoInternazionale), true);
    }
    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS concerto ("
                        + "artista TEXT NOT NULL, "
                        + "luogo TEXT NOT NULL, "
                        + "data TEXT NOT NULL, "
                        + "prezzo REAL NOT NULL, "
                        + "concertoInternazionale BOOLEAN NOT NULL)";
                conn.createStatement().execute(createTableSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}