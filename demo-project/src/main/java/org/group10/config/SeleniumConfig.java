package org.group10.config;

import org.group10.crawler.property.SeleniumProperty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.group10.crawler.helper.WebDriverHelper.threadSleep;

public class SeleniumConfig {
    private static SeleniumProperty seleniumProperty;

    public SeleniumConfig() {
        seleniumProperty = new SeleniumProperty();
    }

    public WebDriver initBrowser() {
        System.setProperty(seleniumProperty.getDriverType(), seleniumProperty.getDriverPath());
        String extensionPath = seleniumProperty.getExtensionPath();
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(extensionPath));
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        threadSleep(5000);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(tabs.get(0));

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        threadSleep(5000);
        return driver;
    }

}
