package backend.utils.test;

import backend.ConfigModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import backend.controller.CrawlController;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.nft.NFT;
import backend.model.post.Tweet;

import java.util.List;

public class NFTTest {
    public static void main(String[] args) {
        System.out.println("test  controller nft");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        List<NFT> nftList = nftController.getAll();
        System.out.println(nftList.get(2));
        System.out.println(nftController.getByName("MATR1X KUKU").getDetail().getSocialMedia());


    }
}
