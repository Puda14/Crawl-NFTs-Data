package gui.controller.nft;

import backend.dto.nftpricefloorapi.SocialMedia;
import backend.dto.nftpricefloorapi.Marketplace;
import backend.model.nft.Detail;
import backend.model.nft.NFT;
import backend.model.nft.PriceHistory;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.util.StringConverter;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.application.Platform;

public class NftDetailsController {

    @FXML
    private Text contractText;

    @FXML
    private Label nameLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Label blockchainLabel;

    @FXML
    private Label totalSupplyLabel;

    @FXML
    private ListView<SocialMedia> socialMediaListView;

    @FXML
    private ListView<Marketplace> marketplacesListView;

    @FXML
    private LineChart<Number, Number> priceHistoryChart;

    public void setNFTDetails(NFT nft) {
        Detail nftDetails = nft.getDetail();

        nameLabel.setText("Name: " + nftDetails.getName());
        releaseDateLabel.setText("Release Date: " + nftDetails.getReleaseDate());
        contractText.setText("Contract: " + nftDetails.getContract());
        blockchainLabel.setText("BlockChain: " + nftDetails.getBlockchain());
        totalSupplyLabel.setText("Total Supply: " + nftDetails.getTotalSupply());

        // Set up Social Media ListView with anonymous class
        socialMediaListView.setCellFactory(param -> new ListCell<SocialMedia>() {
            @Override
            protected void updateItem(SocialMedia item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getName());
                    setOnMouseClicked(event -> openURL(item.getUrl()));
                } else {
                    setText(null);
                }
            }
        });

        socialMediaListView.getItems().setAll(nftDetails.getSocialMedia());

        // Set up Marketplaces ListView with anonymous class
        marketplacesListView.setCellFactory(param -> new ListCell<Marketplace>() {
            @Override
            protected void updateItem(Marketplace item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getName());
                    setOnMouseClicked(event -> openURL(item.getUrl()));
                } else {
                    setText(null);
                }
            }
        });

        marketplacesListView.getItems().setAll(nftDetails.getMarketplaces());



        List<PriceHistory> priceHistoryList = nft.getPriceHistoryList();
//        WebEngine webEngine = chartWebView.getEngine();
//        webEngine.loadContent();

//        Platform.runLater(() -> {
//            XYChart.Series<Number, Number> series = new XYChart.Series<>();
//            series.setName("Price History");
//
//            for (PriceHistory priceHistory : priceHistoryList) {
//                Double volumeUsd = priceHistory.getVolumeUsd();
//                if (volumeUsd != null) {
//                    series.getData().add(new XYChart.Data<>(priceHistory.getTimestamps().getTime(), volumeUsd));
//                }
//            }
//
//            NumberAxis xAxis = new NumberAxis();
//            xAxis.setLabel("Timestamps");
//
//            NumberAxis yAxis = new NumberAxis();
//            yAxis.setLabel("Volume (USD)");
//
//            priceHistoryChart.getData().add(series);
//        });
    }

    private void openURL(String url) {
        try {
            // Use java.awt.Desktop to open the URL
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}