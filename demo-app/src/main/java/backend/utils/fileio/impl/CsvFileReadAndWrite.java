package backend.utils.fileio.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import backend.model.post.Tweet;
import backend.utils.fileio.FileReadAndWrite;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReadAndWrite implements FileReadAndWrite<Tweet> {
    private static final String[] HEADERS = {"link","account", "timestamp", "tweetText", "reply", "retweet", "like"};

    @Override
    public List<Tweet> readFromFile(Type type, String filePath) {
        List<Tweet> tweets = new ArrayList<>();
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        int line = 0;
        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = csvFormat.parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                line++;
                try {
                    Tweet tweet = new Tweet(
                            csvRecord.get("account"),
                            csvRecord.get("link"),
                            csvRecord.get("timestamp"),
                            csvRecord.get("tweetText"),
                            Integer.parseInt(csvRecord.get("reply")),
                            Integer.parseInt(csvRecord.get("retweet")),
                            Integer.parseInt(csvRecord.get("like"))
                    );
                    tweets.add(tweet);
                } catch (NumberFormatException e) {
                    System.out.println("error at " + line);
                    e.printStackTrace();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tweets;
    }

    @Override
    public void writeToFile(Iterable<Tweet> data, String filePath) {
        boolean fileExists = Files.exists(Paths.get(filePath));
        try (FileWriter writer = new FileWriter(filePath, true);
             CSVPrinter csvPrinter = new CSVPrinter(writer, fileExists ? CSVFormat.DEFAULT : CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (Tweet tweet : data) {
                csvPrinter.printRecord(
                        tweet.getLink(),
                        tweet.getAccount(),
                        tweet.getTimeStamp(),
                        tweet.getTweetText(),
                        tweet.getReply(),
                        tweet.getRetweet(),
                        tweet.getLike()
                );
            }

            csvPrinter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tweet readObjectFromFile(Type type, String filePath) {
        Tweet tweet = null;
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = csvFormat.parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                try {
                        tweet = new Tweet(
                            csvRecord.get("account"),
                            csvRecord.get("link"),
                            csvRecord.get("timestamp"),
                            csvRecord.get("tweetText"),
                            Integer.parseInt(csvRecord.get("reply")),
                            Integer.parseInt(csvRecord.get("retweet")),
                            Integer.parseInt(csvRecord.get("like"))
                    );
                } catch (NumberFormatException e) {
                    e.printStackTrace();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    @Override
    public void writeObjectToFile(Tweet tweet, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS))) {
                csvPrinter.printRecord(
                        tweet.getAccount(),
                        tweet.getLink(),
                        tweet.getTimeStamp(),
                        tweet.getTweetText(),
                        tweet.getReply(),
                        tweet.getRetweet(),
                        tweet.getLike()
                );
            csvPrinter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
