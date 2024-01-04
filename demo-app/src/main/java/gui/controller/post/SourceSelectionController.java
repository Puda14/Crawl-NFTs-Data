package gui.controller.post;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SourceSelectionController {
    @FXML
    private RadioButton twitterRadioButton;

    @FXML
    private RadioButton redditRadioButton;

    @FXML
    private Button selectButton;

    private String selectedSource;

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        twitterRadioButton.setToggleGroup(toggleGroup);
        redditRadioButton.setToggleGroup(toggleGroup);
    }

    @FXML
    private void handleSelectButton() {
        if (twitterRadioButton.isSelected()) {
            selectedSource = "Twitter";
        } else if (redditRadioButton.isSelected()) {
            selectedSource = "Reddit";
        }

        // Close the scene
        Stage stage = (Stage) selectButton.getScene().getWindow();
        stage.close();
    }

    public String getSelectedSource() {
        return selectedSource;
    }
    public void setSelectedSource(String selectedSource){
        this.selectedSource = selectedSource;
    }
}
