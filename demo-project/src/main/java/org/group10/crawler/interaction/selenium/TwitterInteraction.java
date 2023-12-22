package org.group10.crawler.interaction.selenium;

import org.group10.crawler.interaction.WebInteraction;
import org.group10.crawler.property.TwitterProperty;
import org.group10.model.post.Tweet;
import org.openqa.selenium.*;

import static org.group10.crawler.helper.QueryMaker.makeQuery;
import static org.group10.crawler.helper.WebDriverHelper.*;


public class TwitterInteraction implements WebInteraction {

    private final TwitterProperty twitterProperty = new TwitterProperty();
    public static final double AMOUNT_PER_SCROLL = 5000.0;
    public static final String SCROLL_SCRIPT = "window.scrollBy(0, " + (int) AMOUNT_PER_SCROLL + ");";
//    public static final String SCROLL_SCRIPT = "window.scrollTo(0, document.body.scrollHeight)";
    public static final int LONG_DELAY_MS = 5000;

    public static final double FIRST_RELOAD_CONDITION = 1000;
    public static final int SHORT_DELAY_MS = 2000;

    public static final int MAX_SCROLL_ATTEMPTS = 3;
    public static Double lastPosition = -1.0;
    @Override
    public void login(WebDriver driver, String username, String password, String email) throws NoSuchElementException, TimeoutException, StaleElementReferenceException {
        driver.get(twitterProperty.getLoginUrl());
        input(driver,twitterProperty.getUsernameInputField(),username);
        clickButton(driver,twitterProperty.getNextButton());
//        input(driver,twitterProperty.getPasswordInputField(),password);
        try{
            WebElement passwordField = driver.findElement(By.xpath(twitterProperty.getPasswordInputField()));
            passwordField.sendKeys(password);
        }
        catch (Exception e){
            input(driver, twitterProperty.getEmailInputField(), email);
            clickButton(driver,twitterProperty.getNextButton());
            input(driver, twitterProperty.getPasswordInputField(), password);
        }
        clickButton(driver,twitterProperty.getLoginButton());
        threadSleep(LONG_DELAY_MS);
    }

    @Override
    public void logout(WebDriver driver) throws NoSuchElementException, TimeoutException, StaleElementReferenceException {
        driver.get(twitterProperty.getLogoutUrl());
        threadSleep(LONG_DELAY_MS);
        clickButton(driver,twitterProperty.getLogoutButton());
        threadSleep(LONG_DELAY_MS);
    }

    public void search(WebDriver driver, String keyword, String since, String endDate, int min_faves, int min_retweets, int min_replies, int filter_replies) {
        String search_url = "https://twitter.com/search?q=" + makeQuery(keyword, since, endDate, min_faves, min_retweets, min_replies, filter_replies) + "&src=typed_query&f=live";
        driver.get(search_url);
        threadSleep(LONG_DELAY_MS);
    }

    @Override
    public Double scrollDown(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int scrollAttempt = 0;
        while (true) {
            jsExecutor.executeScript(SCROLL_SCRIPT);
//            System.out.println("t vua scroll");
            threadSleep(SHORT_DELAY_MS);
            Object currPositionObject = jsExecutor.executeScript("return window.pageYOffset;");
            if (currPositionObject == null)
                return -1.0;
            Double currPosition = Double.parseDouble(currPositionObject.toString());
            if(currPosition < FIRST_RELOAD_CONDITION){
                return currPosition;
            }
            if (!lastPosition.equals(currPosition)) {
                lastPosition = currPosition;
                return currPosition;
            }
            scrollAttempt++;
            System.out.println(scrollAttempt + " " + currPosition);
            if (scrollAttempt >= MAX_SCROLL_ATTEMPTS) {
                return -1.0 * lastPosition;
            }
        }
    }

}
