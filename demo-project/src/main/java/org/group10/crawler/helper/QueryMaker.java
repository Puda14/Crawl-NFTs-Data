package org.group10.crawler.helper;

import lombok.NoArgsConstructor;

import java.util.Calendar;


public class QueryMaker {
    private static final int DAY_GAP = 3;
    public static String makeQuery(String keyword, String since, int min_faves, int min_retweets, int min_replies, int filter_replies){
        //until = since + 3 days
        String until = null;
        if (since != null) {
            String[] sinceSplit = since.split("-");
            int year = Integer.parseInt(sinceSplit[0]);
            int month = Integer.parseInt(sinceSplit[1]);
            int day = Integer.parseInt(sinceSplit[2]);
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            c.add(Calendar.DATE, DAY_GAP);
            until = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
        }
        String query = keyword + "%20";
        if (min_faves > 0)
            query += "min_faves%3A" + min_faves + "%20";
        if (min_retweets > 0)
            query += "min_retweets%3A" + min_retweets + "%20";
        if (min_replies > 0)
            query += "min_replies%3A" + min_replies + "%20";
        if (filter_replies == 1)
            query += "-filter%3Areplies%20";
        if (since != null)
            query += "since%3A" + since + "%20";
        if (until != null)
            query += " until%3A" + until + "%20";
        return query;
    }

    public static String addDayToString(String startDay, int dayGap) {
        String[] sinceSplit = startDay.split("-");
        int year = Integer.parseInt(sinceSplit[0]);
        int month = Integer.parseInt(sinceSplit[1]);
        int day = Integer.parseInt(sinceSplit[2]);
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day); // month is 0-based
        c.add(Calendar.DATE, dayGap);
        return String.format("%d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));
    }
}
