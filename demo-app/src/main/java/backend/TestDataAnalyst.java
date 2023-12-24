package backend;

import backend.controller.AnalystController;
import backend.dto.twitter.TweetPrice;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
        AnalystController analystController = injector.getInstance(AnalystController.class);

        String start = "2021-08-01";
        String end = "2022-07-30";
        String name = "Bored Ape Yacht Club";
        List<TweetPrice> tweetPriceList = analystController.getTweetAndPriceByTime(name, start,end);
        System.out.println(tweetPriceList);
    }


}
