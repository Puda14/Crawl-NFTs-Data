package gui.controller.hashtag;

import backend.ConfigModule;
import backend.controller.AnalystController;
import backend.dto.twitter.HashtagCount;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import static backend.utils.validate.Validator.isValidDate;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;


public class HashtagController {

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    @FXML
    private Button getTopHashtagButton;

    @FXML
    private TableView<HashtagCount> tableView;

    @FXML
    private TableColumn<HashtagCount, String> hashtagColumn;

    @FXML
    private TableColumn<HashtagCount, Integer> countColumn;

    @FXML
    private TableColumn topColumn;

    private int topCounter = -6;

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
                Injector injector = Guice.createInjector(new ConfigModule());
                AnalystController analystController = injector.getInstance(AnalystController.class);

                // Table of Top Hashtag
                topColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(topCounter++).asObject());                hashtagColumn.setCellValueFactory(new PropertyValueFactory<>("hashtag"));
                countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

                ObservableList<HashtagCount> data = FXCollections.observableArrayList(
                        analystController.getTrendingHashtag(startDate, endDate)
                );

                tableView.setItems(data);
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
