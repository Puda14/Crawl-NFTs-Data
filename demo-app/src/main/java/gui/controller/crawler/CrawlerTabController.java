package gui.controller.crawler;

import backend.ConfigModule;
import backend.controller.CrawlController;
import backend.model.nft.NFT;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import gui.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static backend.env.NftSlugList.slugs;
import static backend.utils.validate.Validator.isValidDate;
import static backend.utils.validate.Validator.isValidKeyword;

public class CrawlerTabController {
    @FXML
    private Button crawlPostButton;

    @FXML
    private Button crawlNFTButton;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    private boolean isCrawling = false;

    @FXML
    private ComboBox<String> nftComboBox;

    private String nftSlugName;
    private String selectedDirectory;


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

        // Gọi showDirectoryChooser ở đây
        Platform.runLater(this::showDirectoryChooser);

        // Action event for the crawlButton
        crawlPostButton.setOnAction(event -> {
            // Check if crawling is already in progress
            if (isCrawling) {

                return;
            }

            // Set crawling flag to true
            isCrawling = true;
            crawlPostButton.setText("Crawling");
            crawlPostButton.setDisable(true);
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
                CrawlController crawlPostController = injector.getInstance(CrawlController.class);

                // Asynchronously crawl tweet data
                CompletableFuture<List<Tweet>> crawlFuture = CompletableFuture.supplyAsync(() ->
                        crawlPostController.crawlTweetDataByKeyword(keyword, startDate, endDate, selectedDirectory));

                // Handle the completion of the crawlFuture
                crawlFuture.thenAccept(tweets -> {
                    System.out.println(tweets);

                    // Update UI on the JavaFX application thread
                    Platform.runLater(() -> {

                        notification();

                        // Reset crawling flag and enable input fields and button
                        isCrawling = false;
                        crawlPostButton.setText("Crawl Post");
                        enableInputFieldsAndButton();
                        keywordTextField.clear();
                        startDateTextField.clear();
                        endDateTextField.clear();
                    });
                });
            } else {

                // Reset crawling flag and enable input fields and button
                isCrawling = false;
                enableInputFieldsAndButton();

            }
        });

        // Disable crawlButton initially
        crawlPostButton.setDisable(true);

        nftComboBox.getItems().addAll(slugs);

        updateCrawlNFTButtonState();


        nftComboBox.valueProperty().addListener((observableNFT, oldValueNFT, newValueNFT) -> {
            System.out.println("NFT selected: " + newValueNFT);
            nftSlugName = newValueNFT;
            updateCrawlNFTButtonState();
        });


        crawlNFTButton.setOnAction(event -> {
            if (!isCrawling) {
                // Crawl NFT
                crawlNFTButton.setText("Crawling");
                // Use Guice to inject dependencies and create CrawlController
                Injector injector = Guice.createInjector(new ConfigModule());
                CrawlController crawlController = injector.getInstance(CrawlController.class);

                // Asynchronously crawl tweet data
                CompletableFuture<NFT> nftCrawlFuture = CompletableFuture.supplyAsync(() ->
                        crawlController.crawlNftBySlug(nftSlugName, selectedDirectory));

                nftCrawlFuture.thenAccept(nft -> {
                    System.out.println(nft);

                    // Update UI on the JavaFX application thread
                    Platform.runLater(() -> {

                        notification();

                        // Reset crawling flag and enable input fields and button
                        isCrawling = false;
                        crawlNFTButton.setText("Crawl NFT");
                        enableInputFieldsAndButton();
                    });
                });

                isCrawling = false;
                updateCrawlNFTButtonState();
            }
        });
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
            crawlPostButton.setDisable(false);
        } else {
            crawlPostButton.setDisable(true);
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
        crawlPostButton.setDisable(false);
    }

    private void updateCrawlNFTButtonState() {
        if (nftSlugName == null || isCrawling) {
            crawlNFTButton.setDisable(true);
        } else {
            crawlNFTButton.setDisable(false);
        }
    }

    private void notification(){
        // Show an information alert upon successful crawl
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText("Crawl complete");
        alert.showAndWait();
    }

    private void showDirectoryChooser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText("You need to choose an address for the crawl data file!");
        alert.showAndWait();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = MainApplication.getPrimaryStage();
        directoryChooser.setTitle("Choose Directory");
        File selectedDirectory = directoryChooser.showDialog(stage.getScene().getWindow());
        if (selectedDirectory != null) {
            System.out.println("Path: " + selectedDirectory.getAbsolutePath());
            this.selectedDirectory = selectedDirectory.getAbsolutePath();
        } else {
            System.out.println("No path");
        }
    }

}
