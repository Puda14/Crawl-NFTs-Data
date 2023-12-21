package org.group10;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.group10.controller.CrawlController;
import org.group10.controller.NFTController;
import org.group10.controller.PostController;
import org.group10.model.nft.NFT;
import org.group10.model.post.Tweet;

import java.util.List;

public class NFTTest {
    public static void main(String[] args) {
        System.out.println("test  controller nft");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        List<NFT> nftList = nftController.getAll();
        System.out.println(nftList.get(2));
        System.out.println(nftController.getByName("Azuki"));


    }
}
