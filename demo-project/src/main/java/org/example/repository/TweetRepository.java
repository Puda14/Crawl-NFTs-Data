package org.example.repository;

import org.example.fileio.FileReadAndWrite;
import org.example.fileio.impl.JsonFileReadAndWrite;
import org.example.model.post.Tweet;

import java.util.List;

import static org.example.env.FileProperty.tweetFilePath;

public class TweetRepository implements BaseRepository<Tweet, String>{

    private FileReadAndWrite<Tweet> fileReadAndWrite = new JsonFileReadAndWrite<>(tweetFilePath);
    @Override
    public List<Tweet> findAll() {
        return fileReadAndWrite.readFromFile();
    }

    @Override
    public void saveAll(Iterable<Tweet> entities) {
        fileReadAndWrite.writeToFile((List<Tweet>) entities);
    }

}
