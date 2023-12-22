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
//        Double last = 287508.0;
//        System.out.println(last.compareTo(LOADED_SOME_TWITTER));
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        CrawlController crawlController = injector.getInstance(CrawlController.class);

        List<Tweet> tweetList = crawlController.crawlTweetDataByKeyword("boredapeyc", "2021-11-01", "2021-11-30");
//        System.out.println(tweetList);


    }
}
