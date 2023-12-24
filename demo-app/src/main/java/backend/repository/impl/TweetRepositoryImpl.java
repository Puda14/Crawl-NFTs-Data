package backend.repository.impl;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import backend.repository.TweetRepository;
import backend.utils.fileio.FileReadAndWrite;
import backend.model.post.Tweet;

import java.util.ArrayList;
import java.util.List;

import static backend.env.FilePath.tweetFilePath;

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

    @Override
    public List<Tweet> findByKeyWordOrAccount(String keyword) {
        List<Tweet> results = new ArrayList<>();
        List<Tweet> tweets = fileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),tweetFilePath);
        for (Tweet tweet : tweets){
            if (tweet.getTweetText().contains(keyword) || tweet.getAccount().contains(keyword)){
                results.add(tweet);
            }
        }
        if(results.size() == 0) return null;
        return  results;
    }


}
