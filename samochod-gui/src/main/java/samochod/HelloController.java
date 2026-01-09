package samochod;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private ObservableList<Samochod> listaSamochodow = FXCollections.observableArrayList();
    private Samochod aktualnySamochod;
    private AnimationTimer timer;

    @FXML private ComboBox<Samochod> autoComboBox;
    @FXML private TextField modelGlowneField;
    @FXML private TextField nrGlowneField;
    @FXML private TextField wagaGlowneField;
    @FXML private TextField stanSprzeglaField;
    @FXML private TextField biegField;
    @FXML private TextField predkoscField;
    @FXML private TextField obrotyField;
    @FXML private VBox autoContainer;
    @FXML private AnchorPane mapaPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Dodanie startowych samochodów
        listaSamochodow.add(new Samochod("Skoda Fabia", "KR 12345", 1500));

        autoComboBox.setItems(listaSamochodow);

        // Wybranie pierwszego auta na start
        if (!listaSamochodow.isEmpty()) {
            autoComboBox.getSelectionModel().selectFirst();
            aktualnySamochod = autoComboBox.getValue();
            wyswietlDaneStatyczne();
        }

        stanSprzeglaField.setText("Zwolnione");

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (aktualnySamochod != null) {
                    int predkosc = aktualnySamochod.getAktPredkosc();
                    if (predkosc > 0) {
                        double aktualnyTranslate = autoContainer.getTranslateX();
                        double layoutX = autoContainer.getLayoutX();
                        double szerokoscMapy = mapaPane.getWidth();
                        double nowaPozycja = aktualnyTranslate + (predkosc / 50.0);

                        if (layoutX + nowaPozycja > szerokoscMapy) {
                            autoContainer.setTranslateX(0);
                        } else {
                            autoContainer.setTranslateX(nowaPozycja);
                        }
                    }
                    aktualizujInterfejs();
                }
            }
        };
        timer.start();
    }

    @FXML
    private void onAutoWybrane() {
        aktualnySamochod = autoComboBox.getValue();
        wyswietlDaneStatyczne();
        aktualizujInterfejs();
    }

    private void wyswietlDaneStatyczne() {
        if (aktualnySamochod != null) {
            modelGlowneField.setText(aktualnySamochod.getModel());
            nrGlowneField.setText(aktualnySamochod.getNrRejestracyjny());
            wagaGlowneField.setText(aktualnySamochod.getWaga() + " kg");
        }
    }

    @FXML
    private void onWlaczClick() {
        if (aktualnySamochod != null) aktualnySamochod.wlacz();
    }

    @FXML
    private void onWylaczClick() {
        if (aktualnySamochod != null) aktualnySamochod.wylacz();
    }

    @FXML
    private void onPrzyspieszClick() {
        if (aktualnySamochod == null || !aktualnySamochod.isStanWlaczenia()) return;
        if (!aktualnySamochod.getSprzeglo().isStanSprzegla()) {
            aktualnySamochod.getSilnik().zwiekszObroty();
        }
    }

    @FXML
    private void onZatrzymajClick() {
        if (aktualnySamochod != null) aktualnySamochod.getSilnik().zmniejszObroty();
    }

    @FXML
    private void onNacisnijSprzegloClick() {
        if (aktualnySamochod != null) {
            aktualnySamochod.getSprzeglo().wcisnij();
            stanSprzeglaField.setText("Wciśnięte");
        }
    }

    @FXML
    private void onZwolnijSprzegloClick() {
        if (aktualnySamochod != null) {
            aktualnySamochod.getSprzeglo().zwolnij();
            stanSprzeglaField.setText("Zwolnione");
        }
    }

    @FXML
    private void onZwiekszBiegClick() {
        if (aktualnySamochod == null) return;
        if (aktualnySamochod.getSprzeglo().isStanSprzegla()) {
            aktualnySamochod.getSkrzynia().zwiekszBieg();
        } else {
            pokazBladSprzegla();
        }
    }

    @FXML
    private void onZmniejszBiegClick() {
        if (aktualnySamochod == null) return;
        if (aktualnySamochod.getSprzeglo().isStanSprzegla()) {
            aktualnySamochod.getSkrzynia().zmniejszBieg();
        } else {
            pokazBladSprzegla();
        }
    }

    private void pokazBladSprzegla() {
        int b = aktualnySamochod.getSkrzynia().getAktBieg();
        String nazwa = (b == -1) ? "R" : (b == 0) ? "N" : String.valueOf(b);
        biegField.setText(nazwa + " (Wciśnij sprzęgło!)");
    }

    private void aktualizujInterfejs() {
        if (aktualnySamochod == null) return;

        if (aktualnySamochod.isStanWlaczenia()) {
            if (aktualnySamochod.getSprzeglo().isStanSprzegla()) {
                obrotyField.setText(aktualnySamochod.getSilnik().getObroty() + " (zwolnij sprzęgło!)");
            } else {
                obrotyField.setText(aktualnySamochod.getSilnik().getObroty() + " obr/min");
            }
        } else {
            obrotyField.setText("0 (silnik wył)");
        }

        predkoscField.setText(aktualnySamochod.getAktPredkosc() + " km/h");

        if (!biegField.getText().contains("Wciśnij")) {
            int b = aktualnySamochod.getSkrzynia().getAktBieg();
            biegField.setText((b == -1) ? "R" : (b == 0) ? "N" : String.valueOf(b));
        }
    }

    @FXML
    private void onDodajNowyClick() {
        try {
            javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(getClass().getResource("nowe-auto.fxml"));
            javafx.scene.Parent root = fxmlLoader.load();
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Nowy Samochód");
            javafx.stage.Stage glowneOkno = (javafx.stage.Stage) mapaPane.getScene().getWindow();
            stage.initOwner(glowneOkno);
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}