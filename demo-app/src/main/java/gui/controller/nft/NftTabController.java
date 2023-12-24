package gui.controller.nft;


import backend.ConfigModule;
import backend.controller.NFTController;
import backend.model.nft.NFT;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

public class NftTabController {

    @FXML
    private ListView<NFT> nftListView;


    public void initialize() {
        // Simulate getting a list of NFTs from a data source
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        List<NFT> nfts = nftController.getAll();

        // Set up the cell factory to customize the display in the ListView
        nftListView.setCellFactory(new Callback<ListView<NFT>, ListCell<NFT>>() {
            @Override
            public ListCell<NFT> call(ListView<NFT> param) {
                return new ListCell<NFT>() {
                    @Override
                    protected void updateItem(NFT item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            // Set the cell text to display the desired properties
                            setText("Name: " + item.getDetail().getName() +
                                    "\nBlockchain: " + item.getDetail().getBlockchain() +
                                    "\nRelease Date: " + item.getDetail().getReleaseDate());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        // Populate the ListView with NFTs
        nftListView.getItems().addAll(nfts);
    }

    @FXML
    private void showNFTDetails(MouseEvent event) {
        // Get the selected NFT from the ListView
        NFT selectedNFT = nftListView.getSelectionModel().getSelectedItem();

        // Load the details FXML file and controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/nft-details.fxml"));
        Parent root;
        try {
            root = loader.load();

            // Pass the selected NFT to the controller of the details view
            NftDetailsController detailsController = loader.getController();
            detailsController.setNFTDetails(selectedNFT);

            // Create a new scene and stage for the details view
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("NFT Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}