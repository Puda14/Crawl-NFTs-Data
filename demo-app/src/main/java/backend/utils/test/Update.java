package backend.utils.test;

import backend.ConfigModule;
import backend.controller.AnalystController;
import backend.controller.CrawlController;
import backend.crawler.helper.QueryMaker;
import backend.model.post.Tweet;
import backend.repository.TweetRepository;
import backend.utils.fileio.impl.CsvFileReadAndWrite;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import static backend.crawler.helper.QueryMaker.addDayToString;

public class Update {

    public static void main(String[] args) {
        CsvFileReadAndWrite csvFileReadAndWrite = new CsvFileReadAndWrite();
        List<Tweet> tweets = csvFileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),"boredape.csv");
        String last = tweets.get(tweets.size() - 1).getTimeStamp();
        last = last.substring(0, 10);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String today = now.toString().substring(0, 10);
        today = addDayToString(today, -1);
        String since = addDayToString(last, 1);
        System.out.println(today);
        Injector injector = Guice.createInjector(new ConfigModule());
        CrawlController crawlController = injector.getInstance(CrawlController.class);
//        while (since.compareTo(today) <= 0) {
//            String until = addDayToString(since, 1);
//            List<Tweet> tweetList = crawlController.crawlTweetDataByKeyword("boredapeyc", since, until);
//            since = until;
//        }
    }
}
