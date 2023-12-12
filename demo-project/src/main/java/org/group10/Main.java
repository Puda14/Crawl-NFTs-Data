package org.group10;

import org.group10.model.nft.NFT;
import org.group10.repository.NftRepository;
import org.group10.service.CrawlService;
import org.group10.service.NFTService;


//for testing
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        NFTService nftService = new NFTService();
        CrawlService crawlService = new CrawlService();
//        crawlService.nftCrawl();
        System.out.println(nftService.getAllNft().size());
        NFT nft = nftService.getNftByName("Azuki");
        if(nft != null) System.out.println(nft);

    }
}