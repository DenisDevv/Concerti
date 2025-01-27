package live.denisdev.concerti;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.time.LocalDate;
import java.util.Objects;

public class Staff {
    @FXML
    private TextField art;
    @FXML
    private TextField luo;
    @FXML
    private DatePicker dat;
    @FXML
    private TextField prez;
    @FXML
    private RadioButton nazio;
    @FXML
    private Pane modi;
    @FXML
    private TextField art1;
    @FXML
    private TextField luo1;
    @FXML
    private DatePicker dat1;
    @FXML
    private TextField prez1;
    @FXML
    private RadioButton nazio1;
    @FXML
    private RadioButton inte1;
    @FXML
    private Button nuovo;
    @FXML
    private Button mod;
    @FXML
    private Button elim;
    @FXML
    private Circle logo;
    private Concerti listaConcerti = new Concerti();
    @FXML
    private ListView<Concerto> lista;
    @FXML
    private Pane crea;
    @FXML
    private Pane conferma;
    @FXML
    private Button si;
    @FXML
    private Button no;
    @FXML
    private Label errore;
    private Concerto concertoSelezionato;
    public void setConcerti(Concerti concerti) {
        this.listaConcerti = concerti;
        lista.setItems(listaConcerti.getConcerti());
    }
    @FXML
    protected void initialize() {
        Image im = new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon512.png")));
        logo.setFill(new ImagePattern(im));
        nuovo.setGraphic(new FontIcon("fas-plus"));
        mod.setGraphic(new FontIcon("fas-edit"));
        elim.setGraphic(new FontIcon("fas-trash"));
        si.setGraphic(new FontIcon("fas-check"));
        no.setGraphic(new FontIcon("fas-times"));
        lista.setItems(listaConcerti.getConcerti());
        lista.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selezione());
    }
    @FXML
    protected void nuovo(){
        crea.setVisible(true);
    }
    @FXML
    protected void crea() {
        try {
            checkValues();
        } catch (IllegalArgumentException e) {
            errore.setText(e.getMessage());
            return;
        }
        if (nazio.isSelected()) {
            listaConcerti.addNazionale(art.getText(), luo.getText(), dat.getValue().toString(), Double.parseDouble(prez.getText()));
            Concerti.insertConcerto(art.getText(), luo.getText(), dat.getValue().toString(), Double.parseDouble(prez.getText()), false);
        } else {
            listaConcerti.addInternazionale(art.getText(), luo.getText(), dat.getValue().toString(), Double.parseDouble(prez.getText()));
            Concerti.insertConcerto(art.getText(), luo.getText(), dat.getValue().toString(), Double.parseDouble(prez.getText()), true);
        }
        lista.setItems(listaConcerti.getConcerti());
        crea.setVisible(false);
        pulisciInput();
    }
    protected void checkValues() throws IllegalArgumentException {
        if (art.getText() == null || luo.getText() == null || dat.getValue() == null || prez.getText() == null || art.getText().isEmpty() || luo.getText().isEmpty() || prez.getText().isEmpty()) {
            throw new IllegalArgumentException("Tutti i campi sono obbligatori");
        }
        if (listaConcerti.getConcerti().stream().anyMatch(c -> c.getArtista().equals(art.getText()) && c.getLuogo().equals(luo.getText()) && c.getData().equals(dat.getValue().toString()))) {
            throw new IllegalArgumentException("Concerto già presente");
        }
    }
    protected void checkValues2() throws IllegalArgumentException{
        if (art1.getText() == null || luo1.getText() == null || dat1.getValue() == null || prez1.getText() == null || art1.getText().isEmpty() || luo1.getText().isEmpty() || dat1.getValue().toString().isEmpty() || prez1.getText().isEmpty()) {
            throw new IllegalArgumentException("Tutti i campi sono obbligatori");
        }
        if (listaConcerti.getConcerti().stream().anyMatch(c -> c.getArtista().equals(art1.getText()) && c.getLuogo().equals(luo1.getText()) && c.getData().equals(dat1.getValue().toString())) && !concertoSelezionato.getArtista().equals(art1.getText()) && !concertoSelezionato.getLuogo().equals(luo1.getText()) && !concertoSelezionato.getData().equals(dat1.getValue().toString())) {
            throw new IllegalArgumentException("Concerto già presente");
        }
    }
    @FXML
    protected void modifica() {
        if (concertoSelezionato != null) {
            art1.setText(concertoSelezionato.getArtista());
            luo1.setText(concertoSelezionato.getLuogo());
            int d = Integer.parseInt(concertoSelezionato.getData().split("-")[2]);
            int m = Integer.parseInt(concertoSelezionato.getData().split("-")[1]);
            int y = Integer.parseInt(concertoSelezionato.getData().split("-")[0]);
            dat1.setValue(LocalDate.of(y, m, d));
            prez1.setText(concertoSelezionato.getPrezzo().toString());
            if (concertoSelezionato instanceof ConcertoNazionale) {
                nazio1.setSelected(true);
            } else {
                inte1.setSelected(true);
            }
            listaConcerti.rimConcerto(concertoSelezionato);
            Concerti.deleteConcerto(concertoSelezionato);
            modi.setVisible(true);
        }
    }
    @FXML
    protected void selezione() {
        concertoSelezionato = lista.getSelectionModel().getSelectedItem();
    }
    @FXML
    protected void mod() {
        try {
            checkValues2();
        } catch (IllegalArgumentException e) {
            errore.setText(e.getMessage());
            return;
        }
        listaConcerti.modConcerto(art1.getText(), luo1.getText(), dat1.getValue().toString(), Double.parseDouble(prez1.getText()), nazio1.isSelected());
        Concerti.insertConcerto(art1.getText(), luo1.getText(), dat1.getValue().toString(), Double.parseDouble(prez1.getText()), !nazio1.isSelected());
        lista.setItems(listaConcerti.getConcerti());
        modi.setVisible(false);
        pulisciInput();
    }
    @FXML
    protected void elimina() {
        listaConcerti.rimConcerto(concertoSelezionato);
        Concerti.deleteConcerto(concertoSelezionato);
        lista.setItems(listaConcerti.getConcerti());
        closeConferma();
    }
    private void pulisciInput() {
        art.clear();
        luo.clear();
        dat.setValue(null);
        prez.clear();
        nazio.setSelected(false);
        errore.setText("");
    }
    @FXML
    protected void confermaEliminazione() {
        conferma.setVisible(true);
    }
    @FXML
    protected void closeConferma() {
        conferma.setVisible(false);
    }
}
