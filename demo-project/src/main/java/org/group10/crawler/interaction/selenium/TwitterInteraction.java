package org.group10.crawler.interaction.selenium;

import org.group10.crawler.interaction.WebInteraction;
import org.group10.crawler.property.TwitterProperty;
import org.group10.model.post.Tweet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.group10.crawler.helper.QueryMaker.makeQuery;
import static org.group10.crawler.helper.WebDriverHelper.*;


public class TwitterInteraction implements WebInteraction {

    private TwitterProperty twitterProperty = new TwitterProperty();
    private static final double AMOUNT_PER_SCROLL = 5000.0;
    private static final String SCROLL_SCRIPT = "window.scrollBy(0, " + (int) AMOUNT_PER_SCROLL + ");";
    private static final int LONG_DELAY_MS = 5000;
    private static final int SHORT_DELAY_MS = 1000;

    private static final int MAX_SCROLL_ATTEMPTS = 3;
    private static final int MAX_TWEETS = 1000;
    private static Double lastPosition = -1.0;
    @Override
    public void login(WebDriver driver, String username, String password) {
        driver.get(twitterProperty.getLoginUrl());
        input(driver,twitterProperty.getUsernameInputField(),username);
        clickButton(driver,twitterProperty.getNextButton());
        input(driver,twitterProperty.getPasswordInputField(),password);
        clickButton(driver,twitterProperty.getLoginButton());
        threadSleep(5000);
    }

    @Override
    public void logout(WebDriver driver) {
        driver.get(twitterProperty.getLogoutUrl());
        threadSleep(5000);
        clickButton(driver,twitterProperty.getLogoutButton());
        threadSleep(5000);
    }

    public void search(WebDriver driver, String keyword, String since, int min_faves, int min_retweets, int min_replies, int filter_replies) {
        String search_url = "https://twitter.com/search?q=" + makeQuery(keyword, since, min_faves, min_retweets, min_replies, filter_replies) + "&src=typed_query&f=live";
        driver.get(search_url);
        threadSleep(5000);
    }

    @Override
    public Double scrollDown(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int scrollAttempt = 0;
        while (true) {
            jsExecutor.executeScript(SCROLL_SCRIPT);
            threadSleep(LONG_DELAY_MS);
            Object currPositionObject = jsExecutor.executeScript("return window.pageYOffset;");
            if (currPositionObject == null)
                return -1.0;
            Double currPosition = Double.parseDouble(currPositionObject.toString());
            if(currPosition.compareTo(AMOUNT_PER_SCROLL) < 0)
                return -1.0;
            if (!lastPosition.equals(currPosition)) {
                lastPosition = currPosition;
                return currPosition;
            }
            scrollAttempt++;
            System.out.println(scrollAttempt + " " + currPosition);
            if (scrollAttempt >= MAX_SCROLL_ATTEMPTS) {
                return -1.0;
            }
        }
    }

}
