package backend;

import backend.controller.AnalystController;
import backend.controller.CrawlController;
import backend.controller.NFTController;
import backend.crawler.helper.QueryMaker;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;


import java.util.List;

public class AnalystTest {
    public static void main(String[] args) {
        System.out.println("test crawl tweet");
        Injector injector = Guice.createInjector(new ConfigModule());
        AnalystController analystController = injector.getInstance(AnalystController.class);
        System.out.println(analystController.getTrendingHashtag("2021-08-01", "2022-07-30"));

    }
}
