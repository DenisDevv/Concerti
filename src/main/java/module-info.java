module live.denisdev.concerti {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens live.denisdev.concerti to javafx.fxml;
    exports live.denisdev.concerti;
}