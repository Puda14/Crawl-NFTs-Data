package backend.utils.test;

import backend.ConfigModule;
import backend.controller.AnalystController;
import com.google.inject.Guice;
import com.google.inject.Injector;




public class AnalystTest {
    public static void main(String[] args) {
        System.out.println("test crawl tweet");
        Injector injector = Guice.createInjector(new ConfigModule());
        AnalystController analystController = injector.getInstance(AnalystController.class);
        System.out.println(analystController.getTrendingHashtag("2021-08-01", "2022-07-30"));

    }
}
