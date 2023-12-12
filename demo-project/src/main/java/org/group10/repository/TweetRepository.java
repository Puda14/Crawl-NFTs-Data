package org.group10.repository;

import org.group10.fileio.FileReadAndWrite;
import org.group10.fileio.impl.JsonFileReadAndWrite;
import org.group10.model.post.Tweet;

import java.util.List;

import static org.group10.env.FileProperty.tweetFilePath;

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
