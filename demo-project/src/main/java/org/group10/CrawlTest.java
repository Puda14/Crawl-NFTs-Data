package org.group10;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.group10.controller.CrawlController;
import org.group10.controller.NFTController;
import org.group10.controller.PostController;
import org.group10.model.post.Tweet;

import java.util.List;

public class CrawlTest {
    public static void main(String[] args) {
        System.out.println("test crawl tweet");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        CrawlController crawlController = injector.getInstance(CrawlController.class);

        List<Tweet> tweetList = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-03-13", "2022-03-21");
        System.out.println(tweetList);



    }
}
