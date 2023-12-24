package backend.service;

import backend.dto.twitter.TweetPrice;
import backend.dto.twitter.HashtagCount;

import java.util.List;

public interface AnalystService {
    List<HashtagCount> getTrendingHashtag(String startDate, String endDate);

    List<TweetPrice> getTweetAndPriceByTime(String nftName, String startDate, String endDate);

    Double PearsonCorrelationCalculate(List<TweetPrice> tweetPriceList, String startDate, String endDate);
}
