package backend;

import backend.model.post.Tweet;
import backend.utils.fileio.impl.CsvFileReadAndWrite;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static backend.utils.validate.Validator.isValidKeyword;

public class Test {
    public static void printSt(Integer a, String... strings){
        System.out.println(a);
        System.out.println(strings[0]);
    }

    public static int countZ(List<Tweet> tweets, String month){
        int count = 0;
        for (Tweet tweet:tweets){
            if (tweet.getTimeStamp().contains(month)) count++;
        }
        System.out.print(month + ":   ");
       return count;
    }
    public static void main(String[] args){
        CsvFileReadAndWrite csvFileReadAndWrite = new CsvFileReadAndWrite();
        List<Tweet> tweetList = csvFileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),"boredape.csv");

        System.out.println(countZ(tweetList, "2021-08-01"));
        System.out.println(countZ(tweetList, "2021-08-02"));
        System.out.println(countZ(tweetList, "2021-08-03"));
        System.out.println(countZ(tweetList, "2021-08-04"));
        System.out.println(countZ(tweetList, "2021-08-05"));
        System.out.println(countZ(tweetList, "2021-08-06"));
        System.out.println(countZ(tweetList, "2021-08-07"));
        System.out.println(countZ(tweetList, "2021-08-08"));
        System.out.println(countZ(tweetList, "2021-08-09"));
        System.out.println(countZ(tweetList, "2021-08-10"));
        System.out.println(countZ(tweetList, "2021-08-11"));
        System.out.println(countZ(tweetList, "2021-08-12"));
        System.out.println(countZ(tweetList, "2021-08-13"));
        System.out.println(countZ(tweetList, "2021-08-14"));
        System.out.println(countZ(tweetList, "2021-08-15"));
        System.out.println(countZ(tweetList, "2021-08-16"));
        System.out.println(countZ(tweetList, "2021-08-17"));
        System.out.println(countZ(tweetList, "2021-08-18"));
        System.out.println(countZ(tweetList, "2021-08-19"));
        System.out.println(countZ(tweetList, "2021-08-20"));
        System.out.println(countZ(tweetList, "2021-08-21"));
        System.out.println(countZ(tweetList, "2021-08-22"));
        System.out.println(countZ(tweetList, "2021-08-23"));
        System.out.println(countZ(tweetList, "2021-08-25"));
        System.out.println(countZ(tweetList, "2021-08-26"));
        System.out.println(countZ(tweetList, "2021-08-27"));
        System.out.println(countZ(tweetList, "2021-08-28"));
        System.out.println(countZ(tweetList, "2021-08-29"));
        System.out.println(countZ(tweetList, "2021-08-30"));
        System.out.println(countZ(tweetList, "2021-08-31"));

        System.out.println("Thang 9");
        System.out.println(countZ(tweetList, "2021-09-01"));
        System.out.println(countZ(tweetList, "2021-09-02"));
        System.out.println(countZ(tweetList, "2021-09-03"));
        System.out.println(countZ(tweetList, "2021-09-04"));
        System.out.println(countZ(tweetList, "2021-09-05"));
        System.out.println(countZ(tweetList, "2021-09-06"));
        System.out.println(countZ(tweetList, "2021-09-07"));
        System.out.println(countZ(tweetList, "2021-09-08"));
        System.out.println(countZ(tweetList, "2021-09-09"));
        System.out.println(countZ(tweetList, "2021-09-10"));
        System.out.println(countZ(tweetList, "2021-09-11"));
        System.out.println(countZ(tweetList, "2021-09-12"));
        System.out.println(countZ(tweetList, "2021-09-13"));
        System.out.println(countZ(tweetList, "2021-09-14"));
        System.out.println(countZ(tweetList, "2021-09-15"));
        System.out.println(countZ(tweetList, "2021-09-16"));
        System.out.println(countZ(tweetList, "2021-09-17"));
        System.out.println(countZ(tweetList, "2021-09-18"));
        System.out.println(countZ(tweetList, "2021-09-19"));
        System.out.println(countZ(tweetList, "2021-09-20"));
        System.out.println(countZ(tweetList, "2021-09-21"));
        System.out.println(countZ(tweetList, "2021-09-22"));
        System.out.println(countZ(tweetList, "2021-09-23"));
        System.out.println(countZ(tweetList, "2021-09-25"));
        System.out.println(countZ(tweetList, "2021-09-26"));
        System.out.println(countZ(tweetList, "2021-09-27"));
        System.out.println(countZ(tweetList, "2021-09-28"));
        System.out.println(countZ(tweetList, "2021-09-29"));
        System.out.println(countZ(tweetList, "2021-09-30"));
        System.out.println(countZ(tweetList, "2021-09-31"));

        System.out.println("Thang 10");
        System.out.println(countZ(tweetList, "2021-10-01"));
        System.out.println(countZ(tweetList, "2021-10-02"));
        System.out.println(countZ(tweetList, "2021-10-03"));
        System.out.println(countZ(tweetList, "2021-10-04"));
        System.out.println(countZ(tweetList, "2021-10-05"));
        System.out.println(countZ(tweetList, "2021-10-06"));
        System.out.println(countZ(tweetList, "2021-10-07"));
        System.out.println(countZ(tweetList, "2021-10-08"));
        System.out.println(countZ(tweetList, "2021-10-09"));
        System.out.println(countZ(tweetList, "2021-10-10"));
        System.out.println(countZ(tweetList, "2021-10-11"));
        System.out.println(countZ(tweetList, "2021-10-12"));
        System.out.println(countZ(tweetList, "2021-10-13"));
        System.out.println(countZ(tweetList, "2021-10-14"));
        System.out.println(countZ(tweetList, "2021-10-15"));
        System.out.println(countZ(tweetList, "2021-10-16"));
        System.out.println(countZ(tweetList, "2021-10-17"));
        System.out.println(countZ(tweetList, "2021-10-18"));
        System.out.println(countZ(tweetList, "2021-10-19"));
        System.out.println(countZ(tweetList, "2021-10-20"));
        System.out.println(countZ(tweetList, "2021-10-21"));
        System.out.println(countZ(tweetList, "2021-10-22"));
        System.out.println(countZ(tweetList, "2021-10-23"));
        System.out.println(countZ(tweetList, "2021-10-25"));
        System.out.println(countZ(tweetList, "2021-10-26"));
        System.out.println(countZ(tweetList, "2021-10-27"));
        System.out.println(countZ(tweetList, "2021-10-28"));
        System.out.println(countZ(tweetList, "2021-10-29"));
        System.out.println(countZ(tweetList, "2021-10-30"));
        System.out.println(countZ(tweetList, "2021-10-31"));

//        System.out.println(countZ(tweetList, "2021-09"));
//        System.out.println(countZ(tweetList, "2021-10"));
//        System.out.println(countZ(tweetList, "2021-11"));
//        System.out.println(countZ(tweetList, "2021-12"));
//        System.out.println(countZ(tweetList, "2022-01"));
//        System.out.println(countZ(tweetList, "2022-02"));
//        System.out.println(countZ(tweetList, "2022-03"));
//        System.out.println(countZ(tweetList, "2022-04"));
//        System.out.println(countZ(tweetList, "2022-05"));
//        System.out.println(countZ(tweetList, "2022-06"));
//        System.out.println(countZ(tweetList, "2022-07"));

    }
}
