package backend;

import backend.model.nft.PriceHistory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.nft.NFT;
import backend.model.post.Tweet;

import java.util.List;

public class TestDataAnalyst {

    //hàm đếm số post theo ngày
    public static int countZ(List<Tweet> tweets, String date){
        int count =0;
        for (Tweet tweet : tweets){
            if(tweet.getTimeStamp().contains(date)) count++;
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println("test crawl nft");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        PostController postController = injector.getInstance(PostController.class);


        NFT nft = nftController.getByName("Bored Ape Yacht Club");
        List<PriceHistory> priceHistory = nft.getPriceHistoryList(); // lấy lịch sử giá

        //lấy list tweet
        List<Tweet> tweetList = postController.getAllPost();

        System.out.println("Thang 10");
        System.out.println(countZ(tweetList, "2021-10-01"));
        System.out.println(countZ(tweetList, "2021-10-02"));
        System.out.println(countZ(tweetList, "2021-10-03"));
        System.out.println(countZ(tweetList, "2021-10-04"));
        System.out.println(countZ(tweetList, "2021-10-05"));
        System.out.println(countZ(tweetList, "2021-10-06"));
        System.out.println(countZ(tweetList, "2021-10-07"));
        System.out.println(countZ(tweetList, "2021-10-08"));
        System.out.println(countZ(tweetList, "2021-10-09"));
        System.out.println(countZ(tweetList, "2021-10-10"));
        System.out.println(countZ(tweetList, "2021-10-11"));
        System.out.println(countZ(tweetList, "2021-10-12"));
        System.out.println(countZ(tweetList, "2021-10-13"));
        System.out.println(countZ(tweetList, "2021-10-14"));
        System.out.println(countZ(tweetList, "2021-10-15"));
        System.out.println(countZ(tweetList, "2021-10-16"));
        System.out.println(countZ(tweetList, "2021-10-17"));
        System.out.println(countZ(tweetList, "2021-10-18"));
        System.out.println(countZ(tweetList, "2021-10-19"));
        System.out.println(countZ(tweetList, "2021-10-20"));
        System.out.println(countZ(tweetList, "2021-10-21"));
        System.out.println(countZ(tweetList, "2021-10-22"));
        System.out.println(countZ(tweetList, "2021-10-23"));
        System.out.println(countZ(tweetList, "2021-10-25"));
        System.out.println(countZ(tweetList, "2021-10-26"));
        System.out.println(countZ(tweetList, "2021-10-27"));
        System.out.println(countZ(tweetList, "2021-10-28"));
        System.out.println(countZ(tweetList, "2021-10-29"));
        System.out.println(countZ(tweetList, "2021-10-30"));

    }
}
