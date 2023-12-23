package gui.controller.hashtag;

import backend.model.post.Tweet;
import javafx.fxml.FXML;
import static backend.utils.validate.Validator.isValidDate;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class HashtagController {

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    @FXML
    private Button getTopHashtagButton;

    public void initialize() {

        getTopHashtagButton.setDisable(true);

        // Listener for startDateTextField changes
        startDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        // Listener for endDateTextField changes
        endDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        getTopHashtagButton.setOnAction(event -> {

            // Get input values
            String startDate = startDateTextField.getText();
            String endDate = endDateTextField.getText();
            System.out.println(startDate + " " + endDate);
            if (isValidInput(startDate, endDate)) {

                // Get top Hashtag
            }
        });

    }

    private void validateInputAndUpdateButtonState() {
        String startDate = startDateTextField.getText();
        String endDate = endDateTextField.getText();

        // Enable or disable crawlButton based on input validation
        if (isValidInput(startDate, endDate)) {
            getTopHashtagButton.setDisable(false);
        } else {
            getTopHashtagButton.setDisable(true);
        }
    }

    private boolean isValidInput(String startDate, String endDate) {
        return isValidDate(startDate) && isValidDate(endDate);
    }
}
