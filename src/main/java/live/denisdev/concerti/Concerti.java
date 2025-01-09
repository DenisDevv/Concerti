package live.denisdev.concerti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Concerti {
    private ArrayList<Concerto> concerti = new ArrayList<>();

    public void addNazionale(String artista, String luogo, String data, Double prezzo) {
        concerti.add(new ConcertoNazionale(artista, luogo, data, prezzo));
    }

    public void addInternazionale(String artista, String luogo, String data, Double prezzo) {
        concerti.add(new ConcertoInternazionale(artista, luogo, data, prezzo));
    }

    public ObservableList<Concerto> getConcerti() {
        return FXCollections.observableArrayList(concerti);
    }

    public void rimConcerto(int index) {
        concerti.remove(index);
    }
    public void modConcerto(int index, String artista, String luogo, String data, Double prezzo, boolean nazionale) {
        concerti.set(index, nazionale ? new ConcertoNazionale(artista, luogo, data, prezzo) : new ConcertoInternazionale(artista, luogo, data, prezzo));
    }
}