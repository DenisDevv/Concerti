package live.denisdev.concerti;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Login {
    @FXML
    private TextField email;
    @FXML
    private PasswordField psw;

    @FXML
    protected void login() throws IOException {
        if (email.getText().equals("karajedenis@concerti.it") && psw.getText().equals("karaje")) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("staff.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 700);
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon16.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon32.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon48.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon64.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon128.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon256.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon512.png"))));
            stage.setTitle("Concerti - Staff");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            email.clear();
            psw.clear();
        } else {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("utente.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 700);
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon16.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon32.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon48.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon64.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon128.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon256.png"))));
            stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/live/denisdev/concerti/imgs/icon512.png"))));
            stage.setTitle("Concerti - Utente");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            email.clear();
            psw.clear();
        }
    }
}