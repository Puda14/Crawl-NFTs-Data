package gui.controller.post;
import backend.ConfigModule;
import backend.controller.PostController;

import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class PostTabController {

    @FXML
    private ListView<Tweet> postListView;

    @FXML
    private TextField tweetTextField;

    @FXML
    private Button searchTweetButton;

    @FXML
    private Button allTweetButton;

    public void initialize() {

        Injector injector = Guice.createInjector(new ConfigModule());
        PostController postController = injector.getInstance(PostController.class);
        List<Tweet> tweets = postController.getAllPost();

        postListView.setCellFactory(new Callback<ListView<Tweet>, ListCell<Tweet>>() {
            @Override
            public ListCell<Tweet> call(ListView<Tweet> param) {
                return new ListCell<Tweet>() {
                    @Override
                    protected void updateItem(Tweet item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setOnMouseClicked(event -> openURL(item.getLink()));
                            // Set the cell text to display the desired properties
                            setText("Account: " + item.getAccount() +
                                    "\nContent: " + item.getTweetText() +
                                    "\nLike: " + item.getLike() +
                                    "\nRetweet: " + item.getRetweet() +
                                    "\nReply: " + item.getReply()
                            );
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });


        searchTweetButton.setOnAction(event -> {

            String tweetText = tweetTextField.getText();
            if (!tweetText.isEmpty()) {

                System.out.println("User input: " + tweetText);
                List<Tweet> searchTweets = postController.getByKeyword(tweetText);
                if(searchTweets == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Notification");
                    alert.setHeaderText(null);
                    alert.setContentText("NOT FOUND");
                    alert.showAndWait();
                }
                else {
                    System.out.println("List: " + postController.getByKeyword(tweetText));

                    if(!searchTweets.isEmpty()){
                        postListView.getItems().clear();
                        postListView.getItems().addAll(searchTweets);
                        postListView.setVisible(true);
                    }
                }


                tweetTextField.clear();
            }
        });

        allTweetButton.setOnAction(event -> {
            postListView.getItems().clear();
            postListView.getItems().addAll(tweets);
            postListView.setVisible(true);
        });


        // Populate the ListView with NFTs
        postListView.getItems().addAll(tweets);
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
