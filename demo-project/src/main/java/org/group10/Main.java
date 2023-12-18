package org.group10;

import org.group10.model.nft.NFT;
import org.group10.service.impl.CrawlServiceImpl;
import org.group10.service.impl.NFTServiceImpl;


//for testing
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        NFTServiceImpl nftServiceImpl = new NFTServiceImpl();
        CrawlServiceImpl crawlServiceImpl = new CrawlServiceImpl();
        System.out.println(crawlServiceImpl.postCrawl());
//        crawlServiceImpl.nftCrawlByListOfNft();
//        System.out.println(nftServiceImpl.getAllNft().size());
//        NFT nft = nftServiceImpl.getNftByName("Azuki");
//        if(nft != null) System.out.println(nft);

    }
}