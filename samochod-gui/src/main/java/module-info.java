module samochod.gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens samochod to javafx.fxml;
    exports samochod;
}