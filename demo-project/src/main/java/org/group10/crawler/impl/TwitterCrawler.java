package org.group10.crawler.impl;

import org.group10.crawler.SeleniumCrawler;
import org.group10.crawler.property.TwitterProperty;
import org.group10.model.post.Tweet;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.group10.env.SeleniumProperty.driverPath;
import static org.group10.env.SeleniumProperty.extensionPath;

public class TwitterCrawler implements SeleniumCrawler {
    private static final int DAY_GAP = 3;
    private static final int MIN_FAVES = 5;
    private static final int MIN_RETWEET = 0;
    private static final int MIN_REPLY = 0;
    private static final int FILTER_REPLIES = 1;
    private static final String TWEET_XPATH = "//article[@data-testid='tweet']";
    private static final String USER_NAME_XPATH = ".//div[@data-testid='User-Name']/div";
    private static final String RETWEET_XPATH = ".//div[@data-testid='retweet']";
    private static final String LIKE_XPATH = ".//div[@data-testid='like']";
    private static final String REPLY_XPATH = ".//div[@data-testid='reply']";
    private static final String TIME_XPATH = ".//time";
    private static final String ACCOUNT_LINK = ".//span[contains(text(),'@')]";
    private static final String SCROLL_SCRIPT = "window.scrollTo(0, document.body.scrollHeight);";
    private static final int SCROLL_DELAY_MS = 3000;
    private static final int MAX_SCROLL_ATTEMPTS = 3;
    private static final int MAX_TWEETS = 1000;
    private static Double lastPosition = -1.0;
    public static List<Tweet> Tweets = new ArrayList<>();

    private TwitterProperty twitterProperty = new TwitterProperty();
    public static String pUsername = "crawl_nigh12359";
    //    public static String pEmail = "usetocrawl1@gmail.com";
    public static String pPassword = "onlyforcrawl1";

    public void main(String[] args) {
        //read keyword from file
        String keyword = "boredapeyc";
        run(keyword, "2021-08-01", "2021-08-04");
    }

    private static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void login(WebDriver driver, String username, String password) {
        WebElement emailField = driver.findElement(By.xpath("//input[@name='text']"));
        emailField.sendKeys(username);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();


        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
    }

    private static void search(WebDriver driver, String keyword) {
        WebElement searchBox = driver.findElement(By.xpath("//input[@aria-label=\"Search query\"]"));
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
    }

    private static boolean isAd(WebElement article, String keyword) {
//        //tweet text have no keyword -> ad
//        //is ad by filter through tweet text
        WebElement tweet = article.findElement(By.xpath(".//div[@data-testid='tweetText']"));
        String text = tweet.getText();
        if (text.toLowerCase().contains(keyword.toLowerCase()))
            return false;
        return true;
    }

    private static boolean isAd(WebElement article){
        if(article.isDisplayed())
            return false;
        return true;
    }


    private static String queryMaker(String keyword, String since, int min_faves, int min_retweets, int min_replies, int filter_replies) {
        //until = since + 3 days
        String until = null;
        if (since != null) {
            String[] sinceSplit = since.split("-");
            int year = Integer.parseInt(sinceSplit[0]);
            int month = Integer.parseInt(sinceSplit[1]);
            int day = Integer.parseInt(sinceSplit[2]);
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            c.add(Calendar.DATE, DAY_GAP);
            until = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
        }
        String query = keyword;
        if(min_faves > 0)
            query += " min_faves:" + min_faves;
        if(min_retweets > 0)
            query += " min_retweets:" + min_retweets;
        if(min_replies > 0)
            query += " min_replies:" + min_replies;
        if(filter_replies == 1)
            query += " -filter:replies";
        if (since != null)
            query += " since:" + since;
        if (until != null)
            query += " until:" + until;
        return query;
    }


    @Override
    public void getWebsiteData(WebElement article) {
//        get account href
        WebElement accountWebElement = article.findElement(By.xpath(ACCOUNT_LINK));
        String account = (accountWebElement.getText().replaceAll("\n", " "));
//        get tweet
        WebElement tweetTextWeb = article.findElement(By.xpath(".//div[@data-testid='tweetText']"));
        String tweetText = (tweetTextWeb.getText().replaceAll("\n", " "));
//        get user tag
//        WebElement userNameWebElement = article.findElement(By.xpath(USER_NAME_XPATH));
//        String userName = (userNameWebElement.getText().replaceAll("\n", " "));
//        get time
        WebElement timeWebElement = article.findElement(By.xpath(TIME_XPATH));
        String time = (timeWebElement.getAttribute("datetime").replaceAll("\n", " "));
        //        get reply

        WebElement replyWebElement = article.findElement(By.xpath(REPLY_XPATH));
        int reply;
        if (replyWebElement.getText().replaceAll("\n", "").equals("")) {
            reply = 0;
        } else {
            reply = (Integer.parseInt(replyWebElement.getText().replaceAll("\n", "")));
        }
//        get retweet
        WebElement retweetWebElement = article.findElement(By.xpath(RETWEET_XPATH));
        int retweet;
        if (retweetWebElement.getText().replaceAll("\n", "").equals("")) {
            retweet = 0;
        } else {
            retweet = (Integer.parseInt(retweetWebElement.getText().replaceAll("\n", "")));
        }
//        get like
        int like;
        WebElement likeWebElement = article.findElement(By.xpath(LIKE_XPATH));
        if (likeWebElement.getText().replaceAll("\n", "").equals("")) {
            like = 0;
        } else {
            like = (Integer.parseInt(likeWebElement.getText().replaceAll("\n", "")));
        }
        Tweets.add(new Tweet(account, time, tweetText, reply, retweet, like));
    }

    public static Double scrollAble(WebDriver driver) {
//        jsExecutor.executeScript(SCROLL_SCRIPT);
//        try {
//            Thread.sleep(SCROLL_DELAY_MS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return 0L;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int scrollAttempt = 0;
        while (true) {
            jsExecutor.executeScript(SCROLL_SCRIPT);
            try {
                Thread.sleep(SCROLL_DELAY_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Double currPosition = (Double) jsExecutor.executeScript("return window.pageYOffset;");
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
            if (scrollAttempt > MAX_SCROLL_ATTEMPTS) {
                return -1.0;
            }
        }
    }

    public static String addDayToString(String startDay, int dayGap){
        String[] sinceSplit = startDay.split("-");
        int year = Integer.parseInt(sinceSplit[0]);
        int month = Integer.parseInt(sinceSplit[1]);
        int day = Integer.parseInt(sinceSplit[2]);
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        c.add(Calendar.DATE, dayGap);
        return c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
    }
    public void run(String keyword, String startDay, String endDay) {
        String tmp = startDay;
        while (true) {
//            clearData();
            //read keyword frome file
            String query = queryMaker(keyword, startDay, MIN_FAVES, MIN_RETWEET, MIN_REPLY, FILTER_REPLIES);
            System.setProperty("webdriver.chrome.driver", driverPath);

            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File(extensionPath));
            options.addArguments("--disable-notifications");
            WebDriver driver = new ChromeDriver(options);

            driver.get("https://twitter.com/login");
            driver.manage().window().maximize();

            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            String currentWindowHandle = driver.getWindowHandle();
            driver.switchTo().window(currentWindowHandle);
            threadSleep(1000);
            login(driver, pUsername, pPassword);
            threadSleep(1000);
            search(driver, query);
//        //select profile
//        driver.findElement(By.xpath("//span[contains(text(),'People')]")).click();
//        WebElement profile = driver.findElement(By.xpath("//*[@id='react-root']/div/div/div[2]/main/div/div/div/div/div/div[3]/section/div/div/div[1]/div/div/div/div/div[2]/div/div[1]/div/div[1]/a/div/div[1]/span/span"));
//        profile.click();
            threadSleep(1000);
            driver.findElement(By.xpath("//span[contains(text(),'Latest')]")).click();

//            Object lastPositionObject = jsExecutor.executeScript("return window.pageYOffset;");
//            lastPosition = Double.parseDouble(lastPositionObject.toString());
//        Double lastPosition = (Double) jsExecutor.executeScript("return window.pageYOffset;");
            Double lastPosition = -1.0;
            try {
//            while (lastPosition != -1L) {
                while (scrollAble(driver) != -1.0) {
                    List<WebElement> articles = driver.findElements(By.xpath(TWEET_XPATH));
                    for (WebElement article : articles) {
                        if (isAd(article))
                            continue;
//                    get data from tweet article
                        getWebsiteData(article);
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } finally {
                driver.quit();
            }
            startDay = addDayToString(startDay, DAY_GAP + 1);
//            printToCSV(keyword);
            if(startDay.compareTo(endDay) >= 0){
//                printToCSV(keyword);
                break;
            }
        }
    }

}
