package samochod;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {
    private Samochod samochod = new Samochod();

    @FXML
    private TextField stanSprzeglaField;
    @FXML
    private TextField biegField;

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
            pokazBieg();
        } else {
            pokazBiegGdyNiewcisniete();
        }
    }

    @FXML
    private void onZmniejszBiegClick() {
        if (samochod.getSprzeglo().isStanSprzegla()) {
            samochod.getSkrzynia().zmniejszBieg();
            pokazBieg();
        } else {
            pokazBiegGdyNiewcisniete();
        }
    }

    private void pokazBieg() {
        int b = samochod.getSkrzynia().getAktBieg();
        if (b == -1) {
            biegField.setText("R");
        } else if (b == 0) {
            biegField.setText("N");
        } else {
            biegField.setText(String.valueOf(b));
        }
    }

    private void pokazBiegGdyNiewcisniete() {
        int b = samochod.getSkrzynia().getAktBieg();
        String nazwaBiegu;
        if (b == -1) {
            nazwaBiegu = "R";
        } else if (b == 0) {
            nazwaBiegu = "N";
        } else {
            nazwaBiegu = String.valueOf(b);
        }
        biegField.setText(nazwaBiegu + " (Wciśnij sprzęgło!)");
    }
}