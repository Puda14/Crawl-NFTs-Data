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

        List<Tweet> tweetList20218 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2021-08-01", "2021-08-31");
        List<Tweet> tweetList20219 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2021-09-01", "2021-09-30");
        List<Tweet> tweetList202110 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2021-10-01", "2021-10-31");
        List<Tweet> tweetList202111 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2021-11-01", "2021-11-30");
        List<Tweet> tweetList202112 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2021-12-01", "2021-12-31");
        List<Tweet> tweetList20221 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-01-01", "2022-01-31");
        List<Tweet> tweetList20222 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-02-01", "2022-02-28");
        List<Tweet> tweetList20223 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-03-01", "2022-03-31");
        List<Tweet> tweetList20224 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-04-01", "2022-04-30");
        List<Tweet> tweetList20225 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-05-01", "2022-05-31");
        List<Tweet> tweetList20226 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-06-01", "2022-06-30");
        List<Tweet> tweetList20227 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-07-01", "2022-07-31");
        List<Tweet> tweetList20228 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-08-01", "2022-08-31");
        List<Tweet> tweetList20229 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-09-01", "2022-09-30");
        List<Tweet> tweetList202210 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-10-01", "2022-10-31");
        List<Tweet> tweetList202211 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-11-01", "2022-11-30");
        List<Tweet> tweetList202212 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-12-01", "2022-12-31");
        List<Tweet> tweetList20231 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-01-01", "2023-01-31");
        List<Tweet> tweetList20232 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-02-01", "2023-02-28");
        List<Tweet> tweetList20233 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-03-01", "2023-03-31");
        List<Tweet> tweetList20234 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-04-01", "2023-04-30");
        List<Tweet> tweetList20235 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-05-01", "2023-05-31");
        List<Tweet> tweetList20236 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-06-01", "2023-06-30");
        List<Tweet> tweetList20237 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-07-01", "2023-07-31");
        List<Tweet> tweetList20238 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-08-01", "2023-08-31");
        List<Tweet> tweetList20239 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-09-01", "2023-09-30");
        List<Tweet> tweetList202310 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-10-01", "2023-10-31");
        List<Tweet> tweetList202311 = crawlController.crawlTweetDataByKeyword("boredapeyc", "2023-11-01", "2023-11-30");


//        System.out.println(tweetList);
    }
}
