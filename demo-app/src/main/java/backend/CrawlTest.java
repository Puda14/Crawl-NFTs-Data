package backend;

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

        List<Tweet> tweetList = crawlController.crawlTweetDataByKeyword("boredapeyc", "2021-08-01", "2021-08-03");
        System.out.println(tweetList);

    }
}
