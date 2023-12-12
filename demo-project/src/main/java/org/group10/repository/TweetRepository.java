package org.group10.repository;

import com.google.gson.reflect.TypeToken;
import org.group10.fileio.FileReadAndWrite;
import org.group10.fileio.impl.JsonFileReadAndWrite;
import org.group10.model.post.Tweet;

import java.util.List;

import static org.group10.env.FileProperty.tweetFilePath;

public class TweetRepository implements BaseRepository<Tweet, String>{

    private FileReadAndWrite<Tweet> fileReadAndWrite = new JsonFileReadAndWrite<>(tweetFilePath);
    private List<Tweet> tweets = fileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType());
    @Override
    public List<Tweet> findAll() {
        return tweets;
    }

    @Override
    public List<Tweet> saveAll(List<Tweet> tweets) {
        fileReadAndWrite.writeToFile(tweets);
        return tweets;
    }

    public List<Tweet> findByKeyword(String keyword){
        return null;
    }


}
