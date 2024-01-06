package gui.controller.home;

import backend.ConfigModule;
import backend.controller.CrawlController;
import backend.controller.NFTController;
import backend.controller.PostController;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.text.Text;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HomeController {
    @FXML
    private Button updateButton;
    private boolean isCrawling = false;
    @FXML
    private Text postSize;
    @FXML
    private Text nftSize;
    public void initialize() {

        // Use Guice to inject dependencies and create UpdateController
        Injector injector = Guice.createInjector(new ConfigModule());
        PostController postController = injector.getInstance(PostController.class);
        NFTController nftController = injector.getInstance(NFTController.class);

        int postNumber = postController.getAllPost().size();
        int nftNumber = nftController.getAll().size();
        postSize.setText("Number of Post : " + postNumber);
        nftSize.setText("Number of NFT : " + nftNumber);
        //update button event
        updateButton.setOnAction(event -> {
            // Check if crawling is already in progress
            if (isCrawling) {
                return;
            }
            // Set crawling flag to true
            isCrawling = true;
            updateButton.setDisable(true);
            updateButton.setText("Updating");
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
                    updateButton.setText("Update NFT Data");
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


