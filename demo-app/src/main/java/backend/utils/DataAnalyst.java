package backend.utils;

import backend.model.post.Tweet;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;

import static backend.crawler.helper.QueryMaker.addDayToString;
import static backend.utils.DatetimeFormat.toDate;

public class DataAnalyst {
    public static Pair<Integer,Integer> countPostsInLastNDays(List<Tweet> tweetList, String startDate, int n) {
        int start = lowerBound(tweetList, startDate);
        int end = lowerBound(tweetList, addDayToString(startDate, n));
        int likeCount = 0;
        int count = end-start;
        for(int i = start; i < end; i++) {
            likeCount += tweetList.get(i).getLike();
        }
//        System.out.println(startDate + " " + (end-start));
        return new Pair<>(count, likeCount);
//        int count = 0;
//        String endDate = addDayToString(startDate, n);
////        System.out.println("lastPos: " + lastPos);
//        for(int i = lastPos; i <= tweetList.size() - 1; i++) {
//            String date = tweetList.get(i).getTimeStamp();
////            System.out.printf("date: %s, startDate: %s, endDate: %s\n", date, startDate, endDate);
//            if(date.compareTo(startDate) >= 0 && date.compareTo(endDate) < 0) {
//                count++;
//            }
//            else {
//                lastPos = i;
//                break;
//            }
//        }
//        return new Pair<>(lastPos, count);
    }

    private static int lowerBound(List<Tweet> tweetList, String startDate) {
        int l = 0, r = tweetList.size() - 1;
        while(l < r) {
            int mid = (l + r) / 2;
            if(tweetList.get(mid).getTimeStamp().compareTo(startDate) >= 0) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return l;
    }
}
