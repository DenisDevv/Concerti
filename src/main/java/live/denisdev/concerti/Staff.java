package live.denisdev.concerti;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
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
    @FXML
    protected void initialize() {
        Image im = new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon512.png")));
        logo.setFill(new ImagePattern(im));
        nuovo.setGraphic(new FontIcon("fas-plus"));
        mod.setGraphic(new FontIcon("fas-edit"));
        elim.setGraphic(new FontIcon("fas-trash"));
        lista.setItems(listaConcerti.getConcerti());
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
    protected void mod() {
        // Concerto c = lista.getSelectionModel().getSelectedItem();
        System.out.println(lista.getSelectionModel().getSelectedItem());
        /* if (c != null) {
            art1.setText(c.getArtista());
            luo1.setText(c.getLuogo());
            dat1.setText(c.getData());
            prez1.setText(c.getPrezzo().toString());
            if (c instanceof ConcertoNazionale) {
                nazio1.setSelected(true);
            } else {
                inte1.setSelected(true);
            }
            listaConcerti.rimConcerto(lista.getSelectionModel().getSelectedIndex());
            modi.setVisible(true);
        }
         */
    }
    @FXML
    protected void modifica() {
        listaConcerti.modConcerto(lista.getSelectionModel().getSelectedIndex(), art1.getText(), luo1.getText(), dat1.getText(), Double.parseDouble(prez1.getText()), nazio1.isSelected());
        lista.setItems(listaConcerti.getConcerti());
        modi.setVisible(false);
    }
    @FXML
    protected void elimina() {
        listaConcerti.rimConcerto(lista.getSelectionModel().getSelectedIndex());
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
