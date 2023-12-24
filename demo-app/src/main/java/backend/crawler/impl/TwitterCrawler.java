package backend.crawler.impl;

import com.google.inject.Inject;
import backend.config.SeleniumConfig;
import backend.crawler.SeleniumCrawler;
import backend.crawler.dataprocessor.DataProcessor;
import backend.crawler.interaction.selenium.TwitterInteraction;
import backend.crawler.property.TweetProperty;
import backend.crawler.property.TwitterProperty;
import backend.model.post.Tweet;
import backend.utils.AccountManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static backend.crawler.helper.QueryMaker.addDayToString;
import static backend.crawler.helper.WebDriverHelper.*;
import static backend.env.FilePath.accountFilePath;

public class TwitterCrawler implements SeleniumCrawler<Tweet, Iterable<Tweet>> {
    private static final int DAY_GAP = 3;
    private static final int MIN_FAVES = 5;
    private static final int MIN_RETWEET = 0;
    private static final int MIN_REPLY = 0;
    private static final int FILTER_REPLIES = 0;
    private static final Double FIRST_RELOAD_CONDITION = 1000.0;
    private static final Double LOADED_SOME_TWITTER = -100000.0;
    private static final String TWEET_XPATH = "//article[@data-testid='tweet']";
    private final TwitterInteraction webInteraction;
    private final SeleniumConfig seleniumConfig;
    private final TwitterProperty twitterProperty;
    private final TweetProperty tweetProperty;
    private final DataProcessor<Tweet, TweetProperty> dataProcessor;
    private static final Map<String, Integer> TweetIdMap = new HashMap<>();


    @Inject
    public TwitterCrawler(SeleniumConfig seleniumConfig, TwitterInteraction twitterInteraction, TwitterProperty twitterProperty, TweetProperty tweetProperty, DataProcessor dataProcessor) {
        this.webInteraction = twitterInteraction;
        this.seleniumConfig = seleniumConfig;
        this.twitterProperty = twitterProperty;
        this.tweetProperty = tweetProperty;
        this.dataProcessor = dataProcessor;
    }

    @Override
    public List<Tweet> getWebsiteData(String keyword, String startDay, String endDay) {
        List<Tweet> tweets = new ArrayList<>();
        AccountManager accountManager = new AccountManager(accountFilePath);
        WebDriver driver = seleniumConfig.initBrowser();
        while (startDay.compareTo(endDay) <= 0) {
            String accountDetails[] = accountManager.changeAccount(accountFilePath);
            String pUsername = accountDetails[0];
            String pPassword = accountDetails[1];
            String pEmail = accountDetails[2];
            webInteraction.login(driver, pUsername, pPassword, pEmail);
            webInteraction.search(driver, keyword, startDay, endDay, MIN_FAVES, MIN_RETWEET, MIN_REPLY, FILTER_REPLIES);

            Double lastPosition = -2.0;
            try {
                while (true) {
                    lastPosition = webInteraction.scrollDown(driver);
                    //if reload button detected, break
                    if(lastPosition.compareTo(FIRST_RELOAD_CONDITION * 1.0) < 0 && reloadButtonDetected(driver,twitterProperty.getReloadButton()))
                        break;
                    //if end of page detected, break
                    if(lastPosition.compareTo(0.0) < 0)
                        break;
                    if(noResultDetected(driver, twitterProperty.getNoResult()))
                        break;
                    List<WebElement> articles = driver.findElements(By.xpath(TWEET_XPATH));
                    for (WebElement article : articles) {
                        if (isAdvertisement(article))
                            continue;
                        Tweet tweet = dataProcessor.getElementData(article, tweetProperty);
                        String linkParts[] = tweet.getLink().split("/");
                        String post_id = linkParts[linkParts.length - 1];
                        if (TweetIdMap.get(post_id) != null && TweetIdMap.get(post_id).equals(1)) continue;
                        TweetIdMap.put(post_id, 1);
                        tweets.add(tweet);
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println("last position: " + lastPosition);

            if(lastPosition.compareTo(LOADED_SOME_TWITTER) < 0 || noResultDetected(driver, twitterProperty.getNoResult()))
                startDay = addDayToString(startDay, (DAY_GAP + 1));
            else{
                if(!reloadButtonDetected(driver,twitterProperty.getReloadButton()))
                    startDay = addDayToString(startDay, (DAY_GAP + 1));
            }
            if (startDay.compareTo(endDay) > 0) break;
            System.out.println(startDay + " " + endDay);
            webInteraction.logout(driver);
        }
        driver.quit();
        return tweets;
    }
}
