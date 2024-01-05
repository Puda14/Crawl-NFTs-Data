package backend.utils.test;

import backend.ConfigModule;
import backend.controller.CrawlController;
import backend.controller.UpdateController;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class UpdateNftTest {
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new ConfigModule());
        UpdateController updateController = injector.getInstance(UpdateController.class);
        CrawlController crawlController = injector.getInstance(CrawlController.class);
//         updateController.updateNftData();
//        System.out.println(crawlController.crawlNftBySlug("chromie-squiggle-art-blocks-curated"));
        crawlController.crawlNftData();
    }
}
