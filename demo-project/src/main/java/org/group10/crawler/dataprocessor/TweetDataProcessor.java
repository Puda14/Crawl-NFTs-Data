package org.group10.crawler.dataprocessor;

import org.group10.crawler.property.TweetProperty;
import org.group10.model.post.Tweet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TweetDataProcessor implements DataProcessor<Tweet, TweetProperty> {

    @Override
    public Tweet getElementData(WebElement article, TweetProperty tweetProperty) {
        WebElement linkWebElement = article.findElement(By.xpath(tweetProperty.getLinkProp()));
        String link = linkWebElement.getAttribute("href");
//        get account href
        WebElement accountWebElement = article.findElement(By.xpath(tweetProperty.getAccountProp()));
        String account = (accountWebElement.getText().replaceAll("\n", " "));

//        get time
        WebElement timeWebElement = article.findElement(By.xpath(tweetProperty.getTimeProp()));
        String time = (timeWebElement.getAttribute("datetime").replaceAll("\n", " "));
//get text
        WebElement tweetTextElement = article.findElement(By.xpath(tweetProperty.getTweetTextProp()));
        String tweetText = (tweetTextElement.getText().replaceAll("\n", " "));

        WebElement groupWebElement = article.findElement(By.xpath(tweetProperty.getGroupProp()));
        String group = groupWebElement.getAttribute("aria-label");
        String[] Gparts = group.split(",");
        Integer reply = 0, retweet = 0, like = 0, bookmark = 0;
        for (String part : Gparts) {
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
            bookmark = Integer.parseInt(part.replaceAll("[^0-9]", ""));
        }
        Tweet tweet = new Tweet(link,account,time,tweetText,reply,retweet,like);
        return tweet;
    }

}
