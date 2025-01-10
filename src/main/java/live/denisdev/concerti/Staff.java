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
    private Concerto concertoSelezionato;
    @FXML
    protected void initialize() {
        Image im = new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon512.png")));
        logo.setFill(new ImagePattern(im));
        nuovo.setGraphic(new FontIcon("fas-plus"));
        mod.setGraphic(new FontIcon("fas-edit"));
        elim.setGraphic(new FontIcon("fas-trash"));
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
        if (nazio.isSelected()) {
            listaConcerti.addNazionale(art.getText(), luo.getText(), dat.getText(), Double.parseDouble(prez.getText()));
        } else {
            listaConcerti.addInternazionale(art.getText(), luo.getText(), dat.getText(), Double.parseDouble(prez.getText()));
        }
        lista.setItems(listaConcerti.getConcerti());
        crea.setVisible(false);
        pulisciInput();
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
        lista.setItems(listaConcerti.getConcerti());
        modi.setVisible(false);
    }
    @FXML
    protected void elimina() {
        listaConcerti.rimConcerto(concertoSelezionato);
        lista.setItems(listaConcerti.getConcerti());
    }
    private void pulisciInput() {
        art.clear();
        luo.clear();
        dat.clear();
        prez.clear();
        nazio.setSelected(false);
    }
}
