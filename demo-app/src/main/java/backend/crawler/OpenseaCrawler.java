package backend.crawler;

import com.google.inject.Inject;
import backend.config.SeleniumConfig;
import backend.crawler.SeleniumCrawler;
import backend.crawler.dataprocessor.DataProcessor;
import backend.crawler.interaction.selenium.TwitterInteraction;
import backend.crawler.property.TweetProperty;
import backend.crawler.property.TwitterProperty;
import backend.model.post.Tweet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenseaCrawler {
    private final SeleniumConfig seleniumConfig;

    @Inject
    public OpenseaCrawler(SeleniumConfig seleniumConfig) {
        this.seleniumConfig = seleniumConfig;
    }

    public void Crawl() {
        WebDriver driver = seleniumConfig.initBrowser();

        String url = "https://opensea.io/rankings?sortBy=total_volume";
        driver.get(url);
        String nameXpath = "/html/body/div[1]/div/div[4]/main/div/div[1]/div[3]/div/div[4]/div[13]/div/a";
        WebElement nameElement = driver.findElement(By.xpath(nameXpath));
        String name = nameElement.getText();
        System.out.println(name);
    }
}
