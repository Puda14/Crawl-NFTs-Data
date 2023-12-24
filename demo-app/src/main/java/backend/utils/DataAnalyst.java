package backend.utils;

import backend.model.post.Tweet;

import java.util.Date;
import java.util.List;

import static backend.utils.DatetimeFormat.toDate;

public class DataAnalyst {
    public static int countPostsInLastNDays(List<Tweet> tweetList, Date inputDate, int n) {
        int count = 0;
        long inputTime = inputDate.getTime();
        for (Tweet tweet : tweetList) {
            long tweetTime = toDate(tweet.getTimeStamp()).getTime();
            if (tweetTime >= inputTime - (n-1) * 24 * 60 * 60 * 1000 && tweetTime < inputTime) {
                count++;
            }
        }

        return count;
    }
}
