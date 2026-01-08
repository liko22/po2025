package samochod;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private Samochod samochod = new Samochod();
    private AnimationTimer timer;

    @FXML private TextField stanSprzeglaField;
    @FXML private TextField biegField;
    @FXML private TextField predkoscField;
    @FXML private TextField obrotyField;
    @FXML private VBox autoContainer;
    @FXML private AnchorPane mapaPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stanSprzeglaField.setText("Zwolnione");

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int predkosc = samochod.getAktPredkosc();
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
        };
        timer.start();
    }

    @FXML
    private void onWlaczClick() {
        samochod.wlacz();
    }

    @FXML
    private void onWylaczClick() {
        samochod.wylacz();
    }

    @FXML
    private void onPrzyspieszClick() {
        if (!samochod.isStanWlaczenia()) {
            return;
        }
        if (!samochod.getSprzeglo().isStanSprzegla()) {
            samochod.getSilnik().zwiekszObroty();
        }
    }

    @FXML
    private void onZatrzymajClick() {
        samochod.getSilnik().zmniejszObroty();
    }

    @FXML
    private void onNacisnijSprzegloClick() {
        samochod.getSprzeglo().wcisnij();
        stanSprzeglaField.setText("Wciśnięte");
    }

    @FXML
    private void onZwolnijSprzegloClick() {
        samochod.getSprzeglo().zwolnij();
        stanSprzeglaField.setText("Zwolnione");
    }

    @FXML
    private void onZwiekszBiegClick() {
        if (samochod.getSprzeglo().isStanSprzegla()) {
            samochod.getSkrzynia().zwiekszBieg();
        } else {
            pokazBladSprzegla();
        }
    }

    @FXML
    private void onZmniejszBiegClick() {
        if (samochod.getSprzeglo().isStanSprzegla()) {
            samochod.getSkrzynia().zmniejszBieg();
        } else {
            pokazBladSprzegla();
        }
    }

    private void pokazBladSprzegla() {
        int b = samochod.getSkrzynia().getAktBieg();
        String nazwa = (b == -1) ? "R" : (b == 0) ? "N" : String.valueOf(b);
        biegField.setText(nazwa + " (Wciśnij sprzęgło!)");
    }

    private void aktualizujInterfejs() {
        if (samochod.isStanWlaczenia()) {
            if (samochod.getSprzeglo().isStanSprzegla()) {
                obrotyField.setText(samochod.getSilnik().getObroty() + " (zwolnij sprzęgło!)");
            } else {
                obrotyField.setText(samochod.getSilnik().getObroty() + " obr/min");
            }
        } else {
            obrotyField.setText("0 (silnik wył)");
        }

        predkoscField.setText(samochod.getAktPredkosc() + " km/h");

        if (!biegField.getText().contains("Wciśnij")) {
            int b = samochod.getSkrzynia().getAktBieg();
            biegField.setText((b == -1) ? "R" : (b == 0) ? "N" : String.valueOf(b));
        }
    }
}