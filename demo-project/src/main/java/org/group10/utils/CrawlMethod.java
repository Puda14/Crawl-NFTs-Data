package org.group10.utils;

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
}
