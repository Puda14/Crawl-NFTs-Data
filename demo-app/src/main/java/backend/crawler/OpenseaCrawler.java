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

        String url = "https://nftpricefloor.com/?sort=volume&dir=desc&temp=90d";
        driver.get(url);
        String nameXpath = "/html/body/div[1]/div[2]/div[2]/div[2]/main/table/tbody//td[2]/a";
        List<WebElement> nameElements = driver.findElements(By.xpath(nameXpath));
        for (WebElement nameElement : nameElements) {
            String name = nameElement.getText();
            String link = nameElement.getAttribute("href");
            String[] parts = link.split("/");
            link = parts[parts.length - 1];
            System.out.println(name + " " + link);
        }
    }
}
