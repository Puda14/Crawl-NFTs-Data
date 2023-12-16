package group10.crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface SeleniumCrawler {
    void getWebsiteData(WebElement article);
    void search(WebDriver driver, String query);
}
