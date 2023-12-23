package gui.controller.crawler;

import backend.ConfigModule;
import backend.controller.CrawlController;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static backend.utils.validate.Validator.isValidDate;
import static backend.utils.validate.Validator.isValidKeyword;

public class CrawlerTabController {
    @FXML
    private Button crawlButton;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    private boolean isCrawling = false;

    /**
     * Initializes the controller. Configures listeners for input fields and sets up the crawl button.
     */
    public void initialize() {
        // Listener for keywordTextField changes
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        // Listener for startDateTextField changes
        startDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        // Listener for endDateTextField changes
        endDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        // Action event for the crawlButton
        crawlButton.setOnAction(event -> {
            // Check if crawling is already in progress
            if (isCrawling) {
                crawlButton.setDisable(true);
                return;
            }

            // Set crawling flag to true
            isCrawling = true;

            // Get input values
            String keyword = keywordTextField.getText();
            String startDate = startDateTextField.getText();
            String endDate = endDateTextField.getText();

            // Check if input is valid
            if (isValidInput(keyword, startDate, endDate)) {
                // Print crawl information to console
                System.out.println("Crawl info: " + keyword + startDate + endDate);

                // Use Guice to inject dependencies and create CrawlController
                Injector injector = Guice.createInjector(new ConfigModule());
                CrawlController crawlController = injector.getInstance(CrawlController.class);

                // Asynchronously crawl tweet data
                CompletableFuture<List<Tweet>> crawlFuture = CompletableFuture.supplyAsync(() ->
                        crawlController.crawlTweetDataByKeyword(keyword, startDate, endDate));

                // Handle the completion of the crawlFuture
                crawlFuture.thenAccept(tweets -> {
                    System.out.println(tweets);

                    // Update UI on the JavaFX application thread
                    Platform.runLater(() -> {
                        // Show an information alert upon successful crawl
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Notification");
                        alert.setHeaderText(null);
                        alert.setContentText("Crawl complete");
                        alert.showAndWait();

                        // Reset crawling flag and enable input fields and button
                        isCrawling = false;
                        enableInputFieldsAndButton();
                    });
                });
            } else {
                // Show an error alert for invalid input
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("yyyy-mm-dd");
                alert.showAndWait();

                // Reset crawling flag and enable input fields and button
                isCrawling = false;
                enableInputFieldsAndButton();
            }
        });

        // Disable crawlButton initially
        crawlButton.setDisable(true);
    }

    /**
     * Validates input and updates the state of the crawlButton accordingly.
     */
    private void validateInputAndUpdateButtonState() {
        String keyword = keywordTextField.getText();
        String startDate = startDateTextField.getText();
        String endDate = endDateTextField.getText();

        // Enable or disable crawlButton based on input validation
        if (isValidInput(keyword, startDate, endDate)) {
            crawlButton.setDisable(false);
        } else {
            crawlButton.setDisable(true);
        }
    }

    /**
     * Checks if the input values are valid.
     *
     * @param keyword   The keyword to crawl.
     * @param startDate The start date for crawling.
     * @param endDate   The end date for crawling.
     * @return True if the input is valid, false otherwise.
     */
    private boolean isValidInput(String keyword, String startDate, String endDate) {
        return isValidKeyword(keyword) && isValidDate(startDate) && isValidDate(endDate);
    }

    /**
     * Enables input fields and the crawlButton.
     */
    private void enableInputFieldsAndButton() {
        keywordTextField.setDisable(false);
        startDateTextField.setDisable(false);
        endDateTextField.setDisable(false);
        crawlButton.setDisable(false);
    }
}
