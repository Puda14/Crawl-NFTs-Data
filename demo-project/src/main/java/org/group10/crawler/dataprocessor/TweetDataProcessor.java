package org.group10.crawler.dataprocessor;

import org.group10.crawler.property.TweetProperty;
import org.group10.model.post.Tweet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class TweetDataProcessor implements DataProcessor<Tweet, TweetProperty> {

    @Override
    public Tweet getElementData(WebElement article, TweetProperty tweetProperty) {
        String link, account, time, tweetText;
        Integer reply = 0, retweet = 0, like = 0, bookmark = 0;
        try {
            WebElement linkWebElement = article.findElement(By.xpath(tweetProperty.getLinkProp()));
            link = linkWebElement.getAttribute("href");
        } catch (NoSuchElementException e){
            link = " ";
        }
        //get account href
        WebElement accountWebElement = article.findElement(By.xpath(tweetProperty.getAccountProp()));
        account = (accountWebElement.getText().replaceAll("\n", " "));
        //better now
        //get time
        WebElement timeWebElement = article.findElement(By.xpath(tweetProperty.getTimeProp()));
        time = (timeWebElement.getAttribute("datetime").replaceAll("\n", " "));
        //get text
        try {
            WebElement tweetTextElement = article.findElement(By.xpath(tweetProperty.getTweetTextProp()));
            tweetText = (tweetTextElement.getText().replaceAll("\n", " "));
        } catch(NoSuchElementException e){
            tweetText = " ";
        }
//        tweetText = " ";
        String group;

        try {
            WebElement groupWebElement = article.findElement(By.xpath(tweetProperty.getGroupProp()));
            group = groupWebElement.getAttribute("aria-label");
            String[] groupParts = group.split(",");
            for (String part : groupParts) {
                if (part.contains("repl")) {
                    reply = Integer.parseInt(part.replaceAll("[^0-9]", ""));
                    continue;
                }
                if (part.contains("repost")) {
                    retweet = Integer.parseInt(part.replaceAll("[^0-9]", ""));
                    continue;
                }
                if (part.contains("like")) {
                    like = Integer.parseInt(part.replaceAll("[^0-9]", ""));
                    continue;
                }
//                bookmark = Integer.parseInt(part.replaceAll("[^0-9]", ""));
            }
        }catch (NoSuchElementException e){

        }
        Tweet tweet = new Tweet(link,account,time,tweetText,reply,retweet,like);
        return tweet;
    }

}
