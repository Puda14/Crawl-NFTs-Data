package gui.controller.nft;

import backend.dto.nftpricefloorapi.SocialMedia;
import backend.dto.nftpricefloorapi.Marketplace;
import backend.model.nft.Detail;
import backend.model.nft.NFT;
import backend.model.nft.PriceHistory;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    private ScrollPane scrollPane;
    @FXML
    private LineChart<String, Number> lineChart;

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

        if(nftDetails.getSocialMedia() == null) {
            socialMediaListView.getItems().clear();
        }else {
        socialMediaListView.getItems().setAll(nftDetails.getSocialMedia());
        }

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (lineChart != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();

            for (PriceHistory item : priceHistoryList) {
                if (item.getTimestamps() != null) {
                    String strDate = sdf.format(item.getTimestamps());
                    series.getData().add(new XYChart.Data<>(strDate, item.getFloorUsd()));
                }
            }
            lineChart.setTitle("Price History");
            lineChart.getData().add(series);
        }
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

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