package backend.service.impl;

import backend.dto.twitter.HashtagCount;
import backend.model.post.Tweet;
import backend.repository.TweetRepository;
import backend.service.AnalystService;
import backend.utils.algorithm.ValueComparator;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalystServiceImpl implements AnalystService {
    private final TweetRepository tweetRepository;

    @Inject
    public AnalystServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<HashtagCount> getTrendingHashtag(String startDate, String endDate) {
        List<Tweet> tweetList = tweetRepository.findAll();
        Map<String, Integer> hashtagCount = new HashMap<>();
        List<HashtagCount> hashtags = new ArrayList<>();
        Pattern pattern = Pattern.compile("#\\w+");
        List<String> hashtagList = new ArrayList<>();
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
}
