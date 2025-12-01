package org.example.symulatorgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private static final ObservableList<Samochod> carList = FXCollections.observableArrayList();

    private SkrzyniaBiegow skrzyniaBiegowLogic;

    @FXML
    private ComboBox<Samochod> samochodComboBox;

    @FXML
    private TextField modelTextField;
    @FXML
    private TextField nrRejestracyjnyTextField;
    @FXML
    private TextField wagaTextField;
    @FXML
    private TextField predkoscTextField;

    @FXML
    private TextField skrzyniaNazwaTextField;
    @FXML
    private TextField skrzyniaCenaTextField;
    @FXML
    private TextField skrzyniaWagaTextField;
    @FXML
    private TextField skrzyniaBiegTextField;
    @FXML
    private Button zwiekszBiegButton;
    @FXML
    private Button zmniejszBiegButton;

    @FXML
    private TextField silnikNazwaTextField;
    @FXML
    private TextField silnikCenaTextField;
    @FXML
    private TextField silnikWagaTextField;
    @FXML
    private TextField silnikObrotyTextField;

    @FXML
    private Button dodajNowyButton;

    private static final String NEUTRAL = "N";
    private static final String REVERSE = "R";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        skrzyniaBiegowLogic = new SkrzyniaBiegow(skrzyniaBiegTextField);

        samochodComboBox.setItems(carList);
        samochodComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                displayCarDetails(newVal);
            }
        });

        if (carList.isEmpty()) {
            carList.add(new Samochod("Audi A4", "KR12345", 1450.0, 220, "Diesel", "Automatyczna"));
            samochodComboBox.getSelectionModel().selectFirst();
        }
    }

    private void displayCarDetails(Samochod samochod) {
        modelTextField.setText(samochod.getModel());
        nrRejestracyjnyTextField.setText(samochod.getRegistrationNumber());
        wagaTextField.setText(String.format("%.2f", samochod.getWeight()));
        predkoscTextField.setText(String.valueOf(samochod.getMaxSpeed()));

        skrzyniaNazwaTextField.setText(samochod.getGearboxType());
        skrzyniaCenaTextField.setText("0.00");
        skrzyniaWagaTextField.setText("0.00");
        skrzyniaBiegTextField.setText(NEUTRAL);

        silnikNazwaTextField.setText(samochod.getEngineType());
        silnikCenaTextField.setText("0.00");
        silnikWagaTextField.setText("0.00");
        silnikObrotyTextField.setText("0");
    }

    @FXML
    protected void onZwiekszBiegButtonClick() {
        skrzyniaBiegowLogic.zwiekszBieg();
    }

    @FXML
    protected void onZmniejszBiegButtonClick() {
        skrzyniaBiegowLogic.zmniejszBieg();
    }

    @FXML
    public void openAddCarWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dodaj nowy samochód");

            stage.show();

        } catch (IOException e) {
            System.err.println("Błąd ładowania okna DodajSamochod.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addCarToList(Samochod newSamochod) {
        carList.add(newSamochod);
        System.out.printf("[LOG] Samochód dodany: %s. Całkowita liczba samochodów: %d\n",
                newSamochod.toString(), carList.size());
    }

    public static ObservableList<Samochod> getCarList() {
        return carList;
    }
}