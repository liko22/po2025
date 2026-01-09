package samochod;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController implements Initializable {
    private ObservableList<Samochod> listaSamochodow = FXCollections.observableArrayList();
    private Samochod aktualnySamochod;
    private boolean probaPrzyspieszaniaBezSprzegla = false;

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
    @FXML private ImageView carIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaSamochodow.add(new Samochod("Audi A4", "KR 12345", 1500, 180, 6));
        autoComboBox.setItems(listaSamochodow);

        if (!listaSamochodow.isEmpty()) {
            autoComboBox.getSelectionModel().selectFirst();
            aktualnySamochod = autoComboBox.getValue();
            wyswietlDaneStatyczne();
        }

        mapaPane.setOnMouseClicked(event -> {
            if (aktualnySamochod != null) {
                double x = event.getX();
                double y = event.getY();
                Pozycja nowaPozycja = new Pozycja(x, y);
                aktualnySamochod.jedzDo(nowaPozycja);
            }
        });

        Timer guiTimer = new Timer(true);
        guiTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        }, 0, 20);
    }

    private void refresh() {
        if (aktualnySamochod != null) {
            Platform.runLater(() -> {
                autoContainer.setTranslateX(aktualnySamochod.getPozycja().getX());
                autoContainer.setTranslateY(aktualnySamochod.getPozycja().getY());
                aktualizujInterfejs();
            });
        }
    }

    private void aktualizujInterfejs() {
        if (aktualnySamochod == null) return;

        if (aktualnySamochod.isStanWlaczenia()) {
            String obrotyTekst = aktualnySamochod.getSilnik().getObroty() + " obr/min";
            if (probaPrzyspieszaniaBezSprzegla && aktualnySamochod.getSprzeglo().isStanSprzegla()) {
                obrotyTekst += " (zwolnij sprzeglo!)";
            }
            obrotyField.setText(obrotyTekst);
        } else {
            obrotyField.setText("0 (wył)");
        }

        predkoscField.setText(aktualnySamochod.getAktPredkosc() + " km/h");

        int b = aktualnySamochod.getSkrzynia().getAktBieg();
        String nazwaBiegu = (b == 0) ? "N" : String.valueOf(b);

        if (aktualnySamochod.getSprzeglo().isStanSprzegla()) {
            biegField.setText(nazwaBiegu);
            stanSprzeglaField.setText("Wciśnięte");
        } else {
            stanSprzeglaField.setText("Zwolnione");
            probaPrzyspieszaniaBezSprzegla = false;
            if (!biegField.getText().contains("Wciśnij")) {
                biegField.setText(nazwaBiegu);
            }
        }
    }

    @FXML private void onPrzyspieszClick() {
        if (aktualnySamochod != null && aktualnySamochod.isStanWlaczenia()) {
            if (!aktualnySamochod.getSprzeglo().isStanSprzegla()) {
                aktualnySamochod.getSilnik().zwiekszObroty();
                probaPrzyspieszaniaBezSprzegla = false;
            } else {
                probaPrzyspieszaniaBezSprzegla = true;
            }
        }
    }

    private void pokazBladSprzegla() {
        int b = aktualnySamochod.getSkrzynia().getAktBieg();
        String nazwa = (b == 0) ? "N" : String.valueOf(b);
        biegField.setText(nazwa + " (Wciśnij sprzęgło!)");
    }

    @FXML private void onWlaczClick() { if (aktualnySamochod != null) aktualnySamochod.wlacz(); }
    @FXML private void onWylaczClick() { if (aktualnySamochod != null) aktualnySamochod.wylacz(); }
    @FXML private void onZatrzymajClick() { if (aktualnySamochod != null) aktualnySamochod.getSilnik().zmniejszObroty(); }

    @FXML private void onNacisnijSprzegloClick() {
        if (aktualnySamochod != null) {
            aktualnySamochod.getSprzeglo().wcisnij();
            aktualizujInterfejs();
        }
    }

    @FXML private void onZwolnijSprzegloClick() {
        if (aktualnySamochod != null) {
            aktualnySamochod.getSprzeglo().zwolnij();
            aktualizujInterfejs();
        }
    }

    @FXML private void onZwiekszBiegClick() {
        if (aktualnySamochod != null) {
            if (aktualnySamochod.getSprzeglo().isStanSprzegla()) {
                aktualnySamochod.getSkrzynia().zwiekszBieg();
                aktualizujInterfejs();
            } else {
                pokazBladSprzegla();
            }
        }
    }

    @FXML private void onZmniejszBiegClick() {
        if (aktualnySamochod != null) {
            if (aktualnySamochod.getSprzeglo().isStanSprzegla()) {
                if (aktualnySamochod.getSkrzynia().getAktBieg() > 0) {
                    aktualnySamochod.getSkrzynia().zmniejszBieg();
                    aktualizujInterfejs();
                }
            } else {
                pokazBladSprzegla();
            }
        }
    }

    @FXML private void onAutoWybrane() {
        aktualnySamochod = autoComboBox.getValue();
        if (aktualnySamochod != null) {
            wyswietlDaneStatyczne();
            aktualizujInterfejs();
        }
    }

    @FXML private void onUsunClick() {
        if (aktualnySamochod != null) {
            listaSamochodow.remove(aktualnySamochod);
            if (listaSamochodow.isEmpty()) {
                aktualnySamochod = null;
                czyscPola();
            } else {
                autoComboBox.getSelectionModel().selectFirst();
                onAutoWybrane();
            }
        }
    }

    private void czyscPola() {
        modelGlowneField.clear();
        nrGlowneField.clear();
        wagaGlowneField.clear();
        stanSprzeglaField.clear();
        biegField.clear();
        predkoscField.clear();
        obrotyField.clear();
        autoContainer.setTranslateX(0);
        autoContainer.setTranslateY(0);
    }

    private void wyswietlDaneStatyczne() {
        if (aktualnySamochod != null) {
            modelGlowneField.setText(aktualnySamochod.getModel());
            nrGlowneField.setText(aktualnySamochod.getNrRejestracyjny());
            wagaGlowneField.setText(aktualnySamochod.getWaga() + " kg");
        }
    }

    @FXML
    private void onDodajNowyClick() {
        try {
            javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(getClass().getResource("nowe-auto.fxml"));
            javafx.scene.Parent root = fxmlLoader.load();
            NoweAutoController controller = fxmlLoader.getController();
            controller.setListaSamochodow(listaSamochodow);
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Nowy Samochód");
            stage.initOwner(mapaPane.getScene().getWindow());
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}