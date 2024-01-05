package backend.service.impl;

import backend.dto.twitter.TweetPrice;
import backend.dto.twitter.HashtagCount;
import backend.model.nft.NFT;
import backend.model.nft.PriceHistory;
import backend.model.post.Tweet;
import backend.repository.NftRepository;
import backend.repository.TweetRepository;
import backend.service.AnalystService;
import backend.utils.algorithm.ValueComparator;
import com.google.inject.Inject;
import javafx.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static backend.crawler.helper.QueryMaker.addDayToString;
import static backend.utils.DataAnalyst.countPostsInLastNDays;
import static backend.utils.DatetimeFormat.toDate;
import static backend.utils.algorithm.PearsonCorrelation.calculatePearsonCorrelation;

public class AnalystServiceImpl implements AnalystService {
    private final TweetRepository tweetRepository;
    private final NftRepository nftRepository;

    @Inject
    public AnalystServiceImpl(TweetRepository tweetRepository, NftRepository nftRepository) {
        this.tweetRepository = tweetRepository;
        this.nftRepository = nftRepository;
    }

    @Override
    public List<HashtagCount> getTrendingHashtag(String startDate, String endDate) {
        List<Tweet> tweetList = tweetRepository.findAll();
        Map<String, Integer> hashtagCount = new HashMap<>();
        List<HashtagCount> hashtags = new ArrayList<>();
        Pattern pattern = Pattern.compile("#\\w+");
        startDate = startDate + "T00:00:00.000Z";
        endDate = endDate + "T23:59:59.999Z";

        for(Tweet tweet: tweetList){
            if(tweet.getTimeStamp().compareTo(endDate) > 0 || tweet.getTimeStamp().compareTo(startDate) < 0)
                continue;
            Matcher matcher = pattern.matcher(tweet.getTweetText());
            while(matcher.find()){
                if(hashtagCount.containsKey(matcher.group().toLowerCase())){
                    hashtagCount.put(matcher.group().toLowerCase(), hashtagCount.get(matcher.group().toLowerCase()) + 1);
                }
                else{
                    hashtagCount.put(matcher.group().toLowerCase(), 1);
                }
            }
        }
        ValueComparator bvc = new ValueComparator(hashtagCount);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        sorted_map.putAll(hashtagCount);
        int count = 0;
        for(Map.Entry<String, Integer> entry: sorted_map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
            HashtagCount hashtag = new HashtagCount(entry.getKey(),entry.getValue());
            hashtags.add(hashtag);
            count++;
            if(count == 10)
                break;
        }
        return hashtags;
    }

    @Override
    public List<TweetPrice> getTweetAndPriceByTime(String nftName, String startDate, String endDate) {
        String tempStartDate = startDate;
        startDate = startDate + "T00:00:00.000Z";
        endDate = endDate + "T23:59:59.999Z";
        List<Tweet> tweets = tweetRepository.findAll();
        NFT nft = nftRepository.getOneByName(nftName);
        List<PriceHistory> priceHistory = nft.getPriceHistoryList();
        List<TweetPrice> tweetPriceList = new ArrayList<>();
        for (PriceHistory entry : priceHistory) {
            if(entry.getTimestamps().before(toDate(endDate)) && entry.getTimestamps().after(toDate(startDate))) {
                Date timestamp = entry.getTimestamps();
//                System.out.println(timestamp);
//                int numberOfPostsInLast3Days = countPostsInLastNDays(tweets, timestamp, 3, lastPos);
                Pair<Integer, Integer> pair = countPostsInLastNDays(tweets, tempStartDate, 3);
//                System.out.println(timestamp);
                int numberOfPostsInLast3Days = pair.getKey();
//                System.out.println(tempStartDate + " " + numberOfPostsInLast3Days + " " + lastPos);
                tempStartDate = addDayToString(tempStartDate, 3);
//                int numberOfPostsInLast3Days = 1;
                if(numberOfPostsInLast3Days > 0) {
                    TweetPrice tweetPrice = new TweetPrice();
                    tweetPrice.setPrice(entry.getFloorUsd());
                    tweetPrice.setTimestamp(entry.getTimestamps());
                    tweetPrice.setTweetNumber((double) numberOfPostsInLast3Days);
                    tweetPriceList.add(tweetPrice);
                }
            }
        }
        return tweetPriceList;
    }

    @Override
    public Double PearsonCorrelationCalculate(List<TweetPrice> tweetPriceList, String startDate, String endDate) {
        startDate = startDate + "T00:00:00.000Z";
        endDate = endDate + "T23:59:59.999Z";
        List<Double> variableX = new ArrayList<>();
        List<Double> variableY = new ArrayList<>() ;
        for (TweetPrice tweetPrice : tweetPriceList){
            if(tweetPrice.getTweetNumber() > 1 && tweetPrice.getTimestamp().before(toDate(endDate))
                    &&tweetPrice.getTimestamp().after(toDate(startDate))) {
                variableX.add(tweetPrice.getPrice());
                variableY.add(tweetPrice.getTweetNumber());
            }
        }
        return calculatePearsonCorrelation(variableX, variableY);
    }


}
