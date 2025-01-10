package live.denisdev.concerti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon16.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon32.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon48.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon64.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon128.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon256.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon512.png"))));
        stage.setTitle("Concerti - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:localdata.db")) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS concertos ("
                        + "artista TEXT NOT NULL, "
                        + "luogo TEXT NOT NULL, "
                        + "data TEXT NOT NULL, "
                        + "prezzo REAL NOT NULL, "
                        + "concertoInternazionale BOOLEAN NOT NULL)";
                stmt.execute(createTableSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
        createDatabase();
    }
}