package samochod;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class NoweAutoController {
    @FXML private TextField modelField;
    @FXML private TextField nrField;
    @FXML private TextField wagaField;
    @FXML private TextField predkoscField;
    @FXML private RadioButton bieg5;
    @FXML private RadioButton bieg6;

    private ObservableList<Samochod> listaSamochodow;

    public void setListaSamochodow(ObservableList<Samochod> lista) {
        this.listaSamochodow = lista;
    }

    @FXML
    private void onZapiszClick() {
        try {
            String model = modelField.getText();
            String nr = nrField.getText();
            int waga = Integer.parseInt(wagaField.getText());
            int vMax = Integer.parseInt(predkoscField.getText());
            //ograniczenia tego co wpisujemy  do pola
            if (waga < 800 || waga > 5000) {
                System.out.println("Waga musi być 800-5000");
                return;
            }

            if (vMax < 100 || vMax > 200) {
                System.out.println("Prędkość musi być 100-200");
                return;
            }

            int biegi;
            if (bieg5.isSelected()) {
                biegi = 5;
            } else if (bieg6.isSelected()) {
                biegi = 6;
            } else {
                biegi = 6;
            }

            Samochod nowe = new Samochod(model, nr, waga, vMax, biegi);
            listaSamochodow.add(nowe);

            Stage stage = (Stage) modelField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            System.out.println("Błąd w danych liczbowych!");
        }
    }
}