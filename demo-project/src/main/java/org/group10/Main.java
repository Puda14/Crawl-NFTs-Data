package org.group10;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.group10.controller.NFTController;
import org.group10.model.nft.NFT;
import org.group10.service.impl.CrawlServiceImpl;
import org.group10.service.impl.NFTServiceImpl;

import java.util.List;


//for testing
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController  nftController = injector.getInstance(NFTController.class);
        List<NFT> nftList = nftController.getAll();
        System.out.println(nftList.get(2));
        System.out.println(nftController.getByName("Azuki"));
//        NFTServiceImpl nftServiceImpl = new NFTServiceImpl();
//        CrawlServiceImpl crawlServiceImpl = new CrawlServiceImpl();
//        System.out.println(crawlServiceImpl.postCrawl("boredapeyc", "2022-01-01", "2022-01-03"));
//        crawlServiceImpl.nftCrawlByListOfNft();
//        System.out.println(nftServiceImpl.getAllNft().size());
//        NFT nft = nftServiceImpl.getNftByName("Azuki");
//        if(nft != null) System.out.println(nft);

    }
}