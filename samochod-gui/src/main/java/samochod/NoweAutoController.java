package samochod;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NoweAutoController {
    @FXML private TextField modelField;
    @FXML private TextField nrField;
    @FXML private TextField wagaField;

    @FXML
    private void onZapiszClick() {
        String model = modelField.getText();
        String nr = nrField.getText();
        String waga = wagaField.getText();

        if (model.isEmpty() || nr.isEmpty() || waga.isEmpty()) {
            return;
        }

        Stage stage = (Stage) modelField.getScene().getWindow();
        stage.close();
    }
}