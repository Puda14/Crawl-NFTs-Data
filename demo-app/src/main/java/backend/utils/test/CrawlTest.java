package backend.utils.test;

import backend.ConfigModule;
import backend.crawler.helper.QueryMaker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import backend.controller.CrawlController;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.post.Tweet;


import java.util.List;

public class CrawlTest {
    public static void main(String[] args) {
        System.out.println("test crawl tweet");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        CrawlController crawlController = injector.getInstance(CrawlController.class);
        String since = "2023-01-29";
        String path = "demo-app";
        while (since.compareTo("2023-12-31") <= 0) {
            String until = QueryMaker.addDayToString(since, 1);
            List<Tweet> tweetList = crawlController.crawlTweetDataByKeyword("boredapeyc", since, until,path);
            since = until;
        }

//        System.out.println(tweetList);
    }
}
