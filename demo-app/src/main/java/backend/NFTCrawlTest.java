package backend;

import com.google.inject.Guice;
import com.google.inject.Injector;
import backend.controller.CrawlController;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.nft.NFT;
import backend.model.post.Tweet;

import java.util.List;

import static backend.env.NftSlugList.slugList;

public class NFTCrawlTest {
    public static void main(String[] args) {
        System.out.println("test crawl nft");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        CrawlController crawlController = injector.getInstance(CrawlController.class);

        System.out.println(crawlController.crawlNftBySlug(slugList.get(1)));
    }
}
