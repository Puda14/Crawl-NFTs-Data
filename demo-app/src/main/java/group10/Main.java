package group10;

import group10.model.nft.NFT;
import group10.service.impl.CrawlServiceImpl;
import group10.service.impl.NFTServiceImpl;


//for testing
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        NFTServiceImpl nftServiceImpl = new NFTServiceImpl();
        CrawlServiceImpl crawlServiceImpl = new CrawlServiceImpl();
        crawlServiceImpl.nftCrawlByListOfNft();
        System.out.println(nftServiceImpl.getAllNft().size());
        NFT nft = nftServiceImpl.getNftByName("Azuki");
        if(nft != null) System.out.println(nft);

    }
}