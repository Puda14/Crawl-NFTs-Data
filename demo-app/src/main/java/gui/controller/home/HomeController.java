package gui.controller.home;

import backend.ConfigModule;
import backend.controller.CrawlController;
import backend.controller.UpdateController;
import backend.dto.nftpricefloorapi.TopNFT;
import backend.model.nft.NFT;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HomeController {
    @FXML
    private Button updateButton;
    private boolean isCrawling = false;
    public void initialize() {
        // Action event for the crawlButton
        updateButton.setOnAction(event -> {
            // Check if crawling is already in progress
            if (isCrawling) {
                updateButton.setDisable(true);
                return;
            }
            // Set crawling flag to true
            isCrawling = true;
            // Use Guice to inject dependencies and create UpdateController
            Injector injector = Guice.createInjector(new ConfigModule());
            UpdateController updateController = injector.getInstance(UpdateController.class);

            CompletableFuture<List<NFT>> crawlFuture = CompletableFuture.supplyAsync(() ->
                    updateController.updateNftData() );

            // Handle the completion of the crawlFuture
            crawlFuture.thenAccept(nfts -> {
                // Update UI on the JavaFX application thread
                Platform.runLater(() -> {

                    notification();

                    // Reset crawling flag and enable input fields and button
                    isCrawling = false;
                    updateButton.setDisable(false);
                });
            });


        });
    }

        private void notification() {
            // Show an information alert upon successful crawl
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("Update complete");
            alert.showAndWait();
        }

}


