package org.example.symulatorgui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.ResourceBundle;

public class DodajSamochodController implements Initializable {

    @FXML
    private TextField modelTextField;
    @FXML
    private TextField registrationTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField speedTextField;
    @FXML
    private ComboBox<String> engineComboBox;
    @FXML
    private ComboBox<String> gearboxComboBox;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engineComboBox.setItems(FXCollections.observableArrayList(
                "Benzyna", "Diesel", "Elektryczny", "Hybryda"
        ));

        gearboxComboBox.setItems(FXCollections.observableArrayList(
                "Manualna","Automatyczna"
        ));

        engineComboBox.getSelectionModel().selectFirst();
        gearboxComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onConfirmButton() {
        String model = modelTextField.getText();
        String registration = registrationTextField.getText();
        String engineType = engineComboBox.getValue();
        String gearboxType = gearboxComboBox.getValue();

        if (model.trim().isEmpty() || registration.trim().isEmpty() ||
                weightTextField.getText().trim().isEmpty() || speedTextField.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Wypełnij wszystkie pola");
            alert.setContentText("Wszystkie pola są wymagane.");
            alert.showAndWait();
            return;
        }

        double weight;
        int speed;

        try {
            weight = Double.parseDouble(weightTextField.getText().replace(',', '.'));
            speed = Integer.parseInt(speedTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd danych");
            alert.setHeaderText("Niepoprawne dane liczbowe");
            alert.setContentText("Waga i Prędkość maksymalna muszą być poprawnymi liczbami.");
            alert.showAndWait();
            return;
        }

        Samochod newSamochod = new Samochod(model, registration, weight, speed, engineType, gearboxType);

        HelloController.addCarToList(newSamochod);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}