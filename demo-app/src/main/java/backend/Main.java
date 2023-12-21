package backend;

import com.google.inject.Guice;
import com.google.inject.Injector;
import backend.controller.CrawlController;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.nft.NFT;
import backend.model.post.Tweet;
import backend.service.impl.CrawlServiceImpl;
import backend.service.impl.NFTServiceImpl;

import java.util.List;


//for testing
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController  nftController = injector.getInstance(NFTController.class);
        CrawlController crawlController = injector.getInstance(CrawlController.class);
//        List<NFT> nftList = nftController.getAll();
//        System.out.println(nftList.get(2));
//        System.out.println(nftController.getByName("Azuki"));
//        List<Tweet> tweetList = crawlController.crawlTweetDataByKeyword("boredapeyc", "2022-03-13", "2022-03-21");
//        System.out.println(tweetList);
//        NFTServiceImpl nftServiceImpl = new NFTServiceImpl();
//        CrawlServiceImpl crawlServiceImpl = new CrawlServiceImpl();
//        System.out.println(crawlServiceImpl.postCrawl("boredapeyc", "2022-01-01", "2022-01-03"));
//        crawlServiceImpl.nftCrawlByListOfNft();
//        System.out.println(nftServiceImpl.getAllNft().size());
//        NFT nft = nftServiceImpl.getNftByName("Azuki");
//        if(nft != null) System.out.println(nft);
        PostController postController = injector.getInstance(PostController.class);
        List<Tweet> tweets = postController.getAllPost();
        System.out.println(tweets);
        System.out.println("Search By keyword");
        System.out.println(postController.getByKeyword("nft").size());

    }
}