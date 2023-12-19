package org.group10.utils.fileio.impl;

import org.group10.model.post.Tweet;
import org.group10.utils.fileio.FileReadAndWrite;

import java.lang.reflect.Type;
import java.util.List;

public class CsvFileReadAndWrite implements FileReadAndWrite<Tweet> {
    @Override
    public List<Tweet> readFromFile(Type type, String filePath) {
        return null;
    }

    @Override
    public void writeToFile(Iterable<Tweet> data, String filePath) {

    }
}
