package samochod;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class NoweAutoController {
    @FXML private TextField modelField;
    @FXML private TextField nrField;
    @FXML private TextField wagaField;
    @FXML private TextField predkoscField;
    @FXML private RadioButton bieg5;
    @FXML private RadioButton bieg6;
    @FXML private ComboBox<Silnik> silnikCombo;

    private ObservableList<Samochod> listaSamochodow;

    public void setListaSamochodow(ObservableList<Samochod> lista) {
        this.listaSamochodow = lista;
    }

    @FXML
    public void initialize() {
        silnikCombo.setItems(FXCollections.observableArrayList(
                new Silnik("1.3 ECO ", 210.0, 7000.0),
                new Silnik("2.0 TSI", 180.0, 15000.0),
                new Silnik("1.0 ECO", 110.0, 4500.0)
        ));
        silnikCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void onZapiszClick() {
        try {
            String model = modelField.getText();
            String nr = nrField.getText();
            int waga = Integer.parseInt(wagaField.getText());
            int vMax = Integer.parseInt(predkoscField.getText());

            if (waga < 800 || waga > 5000) {
                System.out.println("Waga musi być 800-5000");
                return;
            }

            if (vMax < 100 || vMax > 250) {
                System.out.println("Prędkość musi być 100-250");
                return;
            }

            int biegi = bieg5.isSelected() ? 5 : 6;

            Silnik wzorzec = silnikCombo.getValue();
            Silnik nowySilnik = new Silnik(wzorzec.getNazwa(), wzorzec.getWaga(), wzorzec.getCena());

            Samochod nowe = new Samochod(model, nr, waga, vMax, biegi, nowySilnik);
            listaSamochodow.add(nowe);

            Stage stage = (Stage) modelField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            System.out.println("Błąd w danych liczbowych!");
        }
    }
}