package org.group10.crawler.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CrawlMethod {
    public static String makeQuery(){
        return null;
    }
    public static boolean isAd(WebElement article){
        return article.isDisplayed();
    }

    public static void makeThreadSleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Double scrollAble(WebDriver driver, String scrollScript,
                                    int scrollDelayTime, int maxScrollAttempts){
        Double lastPosition = -1.0;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int scrollAttempt = 0;
        while (true) {
            jsExecutor.executeScript(scrollScript);
            try {
                Thread.sleep(scrollDelayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object currPositionObject = jsExecutor.executeScript("return window.pageYOffset;");
            if (currPositionObject == null)
                return -1.0;
            Double currPosition = Double.parseDouble(currPositionObject.toString());
            System.out.println(currPosition);
            if (!lastPosition.equals(currPosition)) {
                lastPosition = currPosition;
                return currPosition;
            }
            scrollAttempt++;
            if (scrollAttempt > maxScrollAttempts) {
                return -1.0;
            }
        }
    }


}
