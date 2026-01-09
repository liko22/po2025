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
        listaSamochodow.add(new Samochod("Audi A4", "KR 12345", 1500, 180, 6));
        autoComboBox.setItems(listaSamochodow);

        if (!listaSamochodow.isEmpty()) {
            autoComboBox.getSelectionModel().selectFirst();
            aktualnySamochod = autoComboBox.getValue();
            wyswietlDaneStatyczne();
        }

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (aktualnySamochod != null) {
                    autoContainer.setTranslateX(aktualnySamochod.getPozycja().getX());
                    aktualizujInterfejs();
                }
            }
        };
        timer.start();
    }

    @FXML
    private void onAutoWybrane() {
        aktualnySamochod = autoComboBox.getValue();
        if (aktualnySamochod != null) {
            autoContainer.setTranslateX(aktualnySamochod.getPozycja().getX());
            wyswietlDaneStatyczne();
            aktualizujInterfejs();
        }
    }

    @FXML
    private void onUsunClick() {
        if (aktualnySamochod != null) {
            listaSamochodow.remove(aktualnySamochod);
            if (listaSamochodow.isEmpty()) {
                aktualnySamochod = null;
                modelGlowneField.clear();
                nrGlowneField.clear();
                wagaGlowneField.clear();
                stanSprzeglaField.clear();
                biegField.clear();
                predkoscField.clear();
                obrotyField.clear();
                autoContainer.setTranslateX(0);
            } else {
                autoComboBox.getSelectionModel().selectFirst();
                onAutoWybrane();
            }
        }
    }

    private void wyswietlDaneStatyczne() {
        if (aktualnySamochod != null) {
            modelGlowneField.setText(aktualnySamochod.getModel());
            nrGlowneField.setText(aktualnySamochod.getNrRejestracyjny());
            wagaGlowneField.setText(aktualnySamochod.getWaga() + " kg");
        }
    }

    @FXML private void onWlaczClick() { if (aktualnySamochod != null) aktualnySamochod.wlacz(); }
    @FXML private void onWylaczClick() { if (aktualnySamochod != null) aktualnySamochod.wylacz(); }
    @FXML private void onPrzyspieszClick() { if (aktualnySamochod != null && aktualnySamochod.isStanWlaczenia() && !aktualnySamochod.getSprzeglo().isStanSprzegla()) aktualnySamochod.getSilnik().zwiekszObroty(); }
    @FXML private void onZatrzymajClick() { if (aktualnySamochod != null) aktualnySamochod.getSilnik().zmniejszObroty(); }
    @FXML private void onNacisnijSprzegloClick() { if (aktualnySamochod != null) { aktualnySamochod.getSprzeglo().wcisnij(); stanSprzeglaField.setText("Wciśnięte"); } }
    @FXML private void onZwolnijSprzegloClick() { if (aktualnySamochod != null) { aktualnySamochod.getSprzeglo().zwolnij(); stanSprzeglaField.setText("Zwolnione"); } }
    @FXML private void onZwiekszBiegClick() { if (aktualnySamochod != null) { if (aktualnySamochod.getSprzeglo().isStanSprzegla()) aktualnySamochod.getSkrzynia().zwiekszBieg(); else pokazBladSprzegla(); } }
    @FXML private void onZmniejszBiegClick() { if (aktualnySamochod != null) { if (aktualnySamochod.getSprzeglo().isStanSprzegla()) aktualnySamochod.getSkrzynia().zmniejszBieg(); else pokazBladSprzegla(); } }

    private void pokazBladSprzegla() {
        int b = aktualnySamochod.getSkrzynia().getAktBieg();
        String nazwa = (b == -1) ? "R" : (b == 0) ? "N" : String.valueOf(b);
        biegField.setText(nazwa + " (Wciśnij sprzęgło!)");
    }

    private void aktualizujInterfejs() {
        if (aktualnySamochod == null) return;
        if (aktualnySamochod.isStanWlaczenia()) {
            String obrotyTekst = aktualnySamochod.getSilnik().getObroty() + " obr/min";
            if (aktualnySamochod.getSprzeglo().isStanSprzegla()) obrotyTekst += " (zwolnij!)";
            obrotyField.setText(obrotyTekst);
        } else {
            obrotyField.setText("0 (wył)");
        }
        predkoscField.setText(aktualnySamochod.getAktPredkosc() + " km/h");
        int b = aktualnySamochod.getSkrzynia().getAktBieg();
        String nazwaBiegu = (b == -1) ? "R" : (b == 0) ? "N" : String.valueOf(b);
        if (aktualnySamochod.getSprzeglo().isStanSprzegla() || !biegField.getText().contains("Wciśnij")) {
            biegField.setText(nazwaBiegu);
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