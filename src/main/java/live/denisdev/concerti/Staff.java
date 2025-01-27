package live.denisdev.concerti;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Staff {
    @FXML
    private TextField art;
    @FXML
    private TextField luo;
    @FXML
    private TextField dat;
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
    private TextField dat1;
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
        lista.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            selezione();
        });
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
            System.err.println(e.getMessage());
            return;
        }
        if (nazio.isSelected()) {
            listaConcerti.addNazionale(art.getText(), luo.getText(), dat.getText(), Double.parseDouble(prez.getText()));
            Concerti.insertConcerto(art.getText(), luo.getText(), dat.getText(), Double.parseDouble(prez.getText()), false);
        } else {
            listaConcerti.addInternazionale(art.getText(), luo.getText(), dat.getText(), Double.parseDouble(prez.getText()));
            Concerti.insertConcerto(art.getText(), luo.getText(), dat.getText(), Double.parseDouble(prez.getText()), true);
        }
        lista.setItems(listaConcerti.getConcerti());
        crea.setVisible(false);
        pulisciInput();
    }
    protected void checkValues() {
        if (art.getText() == null || luo.getText() == null || dat.getText() == null || prez.getText() == null || art.getText().isEmpty() || luo.getText().isEmpty() || dat.getText().isEmpty() || prez.getText().isEmpty()) {
            throw new IllegalArgumentException("Tutti i campi sono obbligatori");
        }
        if (listaConcerti.getConcerti().stream().anyMatch(c -> c.getArtista().equals(art.getText()) && c.getLuogo().equals(luo.getText()) && c.getData().equals(dat.getText()))) {
            throw new IllegalArgumentException("Concerto già presente");
        }
    }
    @FXML
    protected void modifica() {
        if (concertoSelezionato != null) {
            art1.setText(concertoSelezionato.getArtista());
            luo1.setText(concertoSelezionato.getLuogo());
            dat1.setText(concertoSelezionato.getData());
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
        listaConcerti.modConcerto(art1.getText(), luo1.getText(), dat1.getText(), Double.parseDouble(prez1.getText()), nazio1.isSelected());
        Concerti.insertConcerto(art1.getText(), luo1.getText(), dat1.getText(), Double.parseDouble(prez1.getText()), !nazio1.isSelected());
        lista.setItems(listaConcerti.getConcerti());
        modi.setVisible(false);
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
        dat.clear();
        prez.clear();
        nazio.setSelected(false);
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
