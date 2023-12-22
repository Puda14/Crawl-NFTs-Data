package gui.controller.crawler;

import backend.ConfigModule;
import backend.controller.CrawlController;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;


public class CrawlerTabController {
    @FXML
    private Button crawlButton;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    public void initialize() {
        crawlButton.setOnAction(event -> {
            String keyword = keywordTextField.getText();

            //yyyy-mm-dd
            String startDate = startDateTextField.getText();

            String endDate = endDateTextField.getText();

            System.out.println("crawl info: "+ keyword + startDate + endDate);

            Injector injector = Guice.createInjector(new ConfigModule());
            CrawlController crawlController = injector.getInstance(CrawlController.class);
            List<Tweet> tweets = crawlController.crawlTweetDataByKeyword(keyword, startDate, endDate);
            System.out.println(tweets);
        });
    }

}
