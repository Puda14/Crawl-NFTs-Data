package com.example.demoapp.controller.nft;

import group10.model.nft.Detail;
import group10.model.nft.NFT;
import group10.service.NftService;
import group10.service.impl.NFTServiceImpl;
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

public class NftTabController {
    @FXML
    private ListView<Detail> nftListView;

    NFTServiceImpl nftService = new NFTServiceImpl();
    public void initialize() {
        // Simulate getting a list of NFTs from a data source
        List<NFT> nfts = nftService.getAllNft();
        List<Detail> nftList = new ArrayList<>();
        for (NFT nft: nfts){
            nftList.add(nft.getDetail());
        }

        // Populate the ListView with NFTs
        nftListView.getItems().addAll(nftList);
    }

    @FXML
    private void showNFTDetails(MouseEvent event) {
        // Get the selected NFT from the ListView
        Detail selectedNFT = nftListView.getSelectionModel().getSelectedItem();

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
