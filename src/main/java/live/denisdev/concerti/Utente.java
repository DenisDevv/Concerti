package live.denisdev.concerti;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Objects;

public class Utente {
    @FXML
    private Circle logo;
    @FXML
    private Button compra;
    @FXML
    private Button si;
    @FXML
    private Button no;
    @FXML
    private Button cerca;
    @FXML
    private ListView<Concerto> lista;
    @FXML
    private Pane conferma;
    @FXML
    private Pane filtroRicerca;
    @FXML
    private TextField luogo;
    private Concerti listaConcerti = new Concerti();
    private Concerto concertoSelezionato;
    @FXML
    protected void initialize() {
        Image im = new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon512.png")));
        logo.setFill(new ImagePattern(im));
        si.setGraphic(new FontIcon("fas-check"));
        no.setGraphic(new FontIcon("fas-times"));
        compra.setGraphic(new FontIcon("fas-shopping-cart"));
        cerca.setGraphic(new FontIcon("fas-search"));
        lista.setItems(listaConcerti.getConcerti());
        lista.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            selezione();
        });
    }
    @FXML
    protected void selezione() {
        concertoSelezionato = lista.getSelectionModel().getSelectedItem();
    }
    @FXML
    protected void compra() {
        listaConcerti.rimConcerto(concertoSelezionato);
        Concerti.deleteConcerto(concertoSelezionato);
        lista.setItems(listaConcerti.getConcerti());
        closeConferma();
    }
    @FXML
    protected void compraConferma() {
        conferma.setVisible(true);

    }
    @FXML
    protected void closeConferma() {
        conferma.setVisible(false);
    }
    @FXML
    protected void cerca() {
        filtroRicerca.setVisible(true);
    }
    @FXML
    protected void filtra() {
        lista.setItems(listaConcerti.getConcerti(luogo.getText()));
        filtroRicerca.setVisible(false);
    }
}
