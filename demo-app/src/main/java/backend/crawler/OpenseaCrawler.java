package backend.crawler;

import com.google.inject.Inject;
import backend.config.SeleniumConfig;
import backend.crawler.SeleniumCrawler;
import backend.crawler.dataprocessor.DataProcessor;
import backend.crawler.interaction.selenium.TwitterInteraction;
import backend.crawler.property.TweetProperty;
import backend.crawler.property.TwitterProperty;
import backend.model.post.Tweet;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class OpenseaCrawler {
    private final SeleniumConfig seleniumConfig;

    @Inject
    public OpenseaCrawler(SeleniumConfig seleniumConfig) {
        this.seleniumConfig = seleniumConfig;
    }

    public void Crawl() {
        WebDriver driver = seleniumConfig.initBrowser();

        String url = "https://opensea.io/collection/boredapeyc";
        driver.get(url);
    }
}
