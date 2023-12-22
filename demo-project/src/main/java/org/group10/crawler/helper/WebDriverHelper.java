package org.group10.crawler.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverHelper {
    public static boolean isAdvertisement(WebElement article) {
        if (article.isDisplayed())
            return false;
        return true;
    }

    public static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickButton(WebDriver driver, String property){
        driver.findElement(By.xpath(property)).click();
    }

    public static void input(WebDriver driver, String property, String input){
        WebElement textField = driver.findElement(By.xpath(property));
        textField.sendKeys(input);
    }

    public static boolean noResultDetected(WebDriver driver, String noResultProp){
        try{
            WebElement noResult = driver.findElement(By.xpath(noResultProp));
            return true;
        }
        catch (Exception e){
//            e.printStackTrace();
            return false;
        }
    }

    public static boolean reloadButtonDetected(WebDriver driver, String reloadProp){
        try{
            WebElement reloadButton = driver.findElement(By.xpath(reloadProp));
            return true;
        }
        catch (Exception e){
//            e.printStackTrace();
            return false;
        }
    }


}
