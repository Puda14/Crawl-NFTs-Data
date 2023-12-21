package org.group10.crawler.impl;

import com.google.inject.Inject;
import org.group10.config.SeleniumConfig;
import org.group10.crawler.SeleniumCrawler;
import org.group10.crawler.dataprocessor.DataProcessor;
import org.group10.crawler.interaction.selenium.TwitterInteraction;
import org.group10.crawler.property.TweetProperty;
import org.group10.crawler.property.TwitterProperty;
import org.group10.model.post.Tweet;
import org.group10.utils.AccountManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.group10.crawler.helper.QueryMaker.addDayToString;
import static org.group10.crawler.helper.WebDriverHelper.*;

public class TwitterCrawler implements SeleniumCrawler<Tweet, Iterable<Tweet>> {
    private static final int DAY_GAP = 3;
    private static final int MIN_FAVES = 5;
    private static final int MIN_RETWEET = 0;
    private static final int MIN_REPLY = 0;
    private static final int FILTER_REPLIES = 0;
    private static final Double FIRST_RELOAD_CONDITION = 1000.0;
    private static final String TWEET_XPATH = "//article[@data-testid='tweet']";
    private final TwitterInteraction webInteraction;
    private final SeleniumConfig seleniumConfig;
    private final TwitterProperty twitterProperty;
    private final TweetProperty tweetProperty;
    private final DataProcessor<Tweet,TweetProperty> dataProcessor;
    private static final Map<String, Integer> TweetIdMap = new HashMap<>();


    @Inject
    public TwitterCrawler(SeleniumConfig seleniumConfig, TwitterInteraction twitterInteraction, TwitterProperty twitterProperty,TweetProperty tweetProperty ,DataProcessor dataProcessor){
        this.webInteraction = twitterInteraction;
        this.seleniumConfig = seleniumConfig;
        this.twitterProperty = twitterProperty;
        this.tweetProperty = tweetProperty;
        this.dataProcessor = dataProcessor;
    }

    @Override
    public List<Tweet> getWebsiteData(String keyword, String startDay, String endDay) {
        List<Tweet> tweets = new ArrayList<>();
        AccountManager accountManager = new AccountManager("account.txt");
        String accountDetails[] = accountManager.changeAccount();
        String pUsername = accountDetails[0];
        String pPassword = accountDetails[1];
        WebDriver driver = seleniumConfig.initBrowser();
        webInteraction.login(driver, pUsername, pPassword);
        while (startDay.compareTo(endDay) <= 0) {
            webInteraction.search(driver, keyword, startDay, MIN_FAVES, MIN_RETWEET, MIN_REPLY, FILTER_REPLIES);

            Double lastPosition = -2.0;
//            Double currPosition = -2.0;
            try {
                while (true) {
//                    lastPosition = currPosition;
//                    currPosition = webInteraction.scrollDown(driver);
                    lastPosition = webInteraction.scrollDown(driver);
                    if(lastPosition.compareTo(FIRST_RELOAD_CONDITION * 1.0) < 0){
                        break;
                    }
                    List<WebElement> articles = driver.findElements(By.xpath(TWEET_XPATH));
                    for (WebElement article : articles) {
                        if (isAdvertisement(article))
                            continue;
                        Tweet tweet = dataProcessor.getElementData(article,tweetProperty);
                        String linkParts[] = tweet.getLink().split("/");
                        String post_id = linkParts[linkParts.length - 1];
                        if (TweetIdMap.get(post_id) != null && TweetIdMap.get(post_id).equals(1)) continue;
                        TweetIdMap.put(post_id, 1);
                        tweets.add(tweet);
                        System.out.println(tweet);
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
//            System.out.println("last position: " + lastPosition + " " + currPosition);
            if(reloadButtonDetected(driver,twitterProperty.getReloadButton())){
                webInteraction.logout(driver);
                accountDetails = accountManager.changeAccount();
                pUsername = accountDetails[0];
                pPassword = accountDetails[1];
                System.out.println("change account to " + pUsername + " " + pPassword);
                driver.manage().deleteAllCookies();
                webInteraction.login(driver, pUsername, pPassword);
                continue;
            }

            startDay = addDayToString(startDay, DAY_GAP + 1);
            System.out.println(startDay + " " + endDay);
        }
        driver.quit();
        return tweets;
    }
}
