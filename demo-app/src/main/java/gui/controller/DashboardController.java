package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private StackPane contentArea;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void showHome(ActionEvent event){
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    @FXML
    private void showPost(ActionEvent event){
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/post.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void showNFT(ActionEvent event){
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/nft.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void showCrawler(ActionEvent event){
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/crawl.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void showAnalyst(ActionEvent event){
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/analyst.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void showHashtag(ActionEvent event){
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/trending.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}
