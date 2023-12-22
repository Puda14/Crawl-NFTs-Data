package backend.config;

import com.google.inject.Inject;
import backend.crawler.property.SeleniumProperty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static backend.crawler.helper.WebDriverHelper.threadSleep;
import static backend.crawler.interaction.selenium.TwitterInteraction.LONG_DELAY_MS;
import static backend.crawler.interaction.selenium.TwitterInteraction.SHORT_DELAY_MS;

public class SeleniumConfig {

    private final SeleniumProperty seleniumProperty;

    @Inject
    public SeleniumConfig(SeleniumProperty seleniumProperty) {
        this.seleniumProperty = seleniumProperty;
    }

    public WebDriver initBrowser() {
        System.setProperty(seleniumProperty.getDriverType(), seleniumProperty.getDriverPath());
        String extensionPath = seleniumProperty.getExtensionPath();
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(extensionPath));
//        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
        threadSleep(SHORT_DELAY_MS);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(tabs.get(0));

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        threadSleep(LONG_DELAY_MS);
        return driver;
    }

}
