package com.example.demoapp.controller.nft;

import group10.model.nft.Detail;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NftDetailsController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label contractLabel;
    @FXML
    private Label releaseDateLabel;

    // Add more @FXML annotations for other labels as needed

    public void setNFTDetails(Detail nftDetails) {
        // Parse and set the details for the selected NFT
        nameLabel.setText(nftDetails.getName()); // Replace with the appropriate index for title
        releaseDateLabel.setText(nftDetails.getReleaseDate()); // Replace with the appropriate index for description
        // Set other details as needed
    }
}
