package org.example.symulatorgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private TextField skrzyniaBiegTextField;

    @FXML
    private Button zwiekszBiegButton;

    @FXML
    private Button zmniejszBiegButton;

    private static final int MAX_GEAR = 6;
    private static final String NEUTRAL = "N";
    private static final String REVERSE = "R";

    @FXML
    protected void onZwiekszBiegButtonClick() {
        if (skrzyniaBiegTextField == null) return;

        String currentGearText = skrzyniaBiegTextField.getText().trim();

        if (currentGearText.isEmpty()) {
            skrzyniaBiegTextField.setText(NEUTRAL);
        } else if (currentGearText.equals(NEUTRAL) || currentGearText.equals(REVERSE) || !currentGearText.matches("\\d+")) {
            skrzyniaBiegTextField.setText("1");
        } else {
            try {
                int currentGear = Integer.parseInt(currentGearText);
                if (currentGear < MAX_GEAR) {
                    skrzyniaBiegTextField.setText(String.valueOf(currentGear + 1));
                }
            } catch (NumberFormatException e) {
                skrzyniaBiegTextField.setText(NEUTRAL);
            }
        }
    }

    @FXML
    protected void onZmniejszBiegButtonClick() {
        if (skrzyniaBiegTextField == null) return;

        String currentGearText = skrzyniaBiegTextField.getText().trim();

        if (currentGearText.isEmpty() || !currentGearText.matches("\\d+")) {
            if (currentGearText.equals(NEUTRAL)) {
                skrzyniaBiegTextField.setText(REVERSE);
            } else if (currentGearText.equals(REVERSE)) {
                skrzyniaBiegTextField.setText(REVERSE);
            } else {
                skrzyniaBiegTextField.setText(NEUTRAL);
            }
        } else {
            try {
                int currentGear = Integer.parseInt(currentGearText);
                if (currentGear > 1) {
                    skrzyniaBiegTextField.setText(String.valueOf(currentGear - 1));
                } else if (currentGear == 1) {
                    skrzyniaBiegTextField.setText(NEUTRAL);
                }
            } catch (NumberFormatException e) {
                skrzyniaBiegTextField.setText(NEUTRAL);
            }
        }
    }
}