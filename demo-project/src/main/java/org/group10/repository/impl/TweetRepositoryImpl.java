package org.group10.repository.impl;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import org.group10.repository.TweetRepository;
import org.group10.utils.fileio.FileReadAndWrite;
import org.group10.model.post.Tweet;

import java.util.ArrayList;
import java.util.List;

import static org.group10.env.FileProperty.tweetFilePath;

public class TweetRepositoryImpl implements TweetRepository {

    private final FileReadAndWrite<Tweet> fileReadAndWrite ;

    @Inject
    public TweetRepositoryImpl(FileReadAndWrite<Tweet> fileReadAndWrite) {
        this.fileReadAndWrite = fileReadAndWrite;
    }

    @Override
    public List<Tweet> findAll() {
        List<Tweet> tweets = fileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),tweetFilePath);
        return tweets;
    }

    @Override
    public List<Tweet> saveAll(List<Tweet> tweets) {
        fileReadAndWrite.writeToFile(tweets,tweetFilePath);
        return tweets;
    }

    @Override
    public List<Tweet> findByKeyword(String keyword){
        List<Tweet> results = new ArrayList<>();
        List<Tweet> tweets = fileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),tweetFilePath);
        for (Tweet tweet : tweets){
            if (tweet.getTweetText().contains(keyword)){
                results.add(tweet);
            }
        }
        if(results.size() == 0) return null;
        return  results;
    }


}
