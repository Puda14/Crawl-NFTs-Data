package backend;

import backend.model.post.Tweet;
import backend.utils.fileio.impl.CsvFileReadAndWrite;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.stream.Collectors;

import static backend.utils.validate.Validator.isValidKeyword;

public class Test {
    public static void printSt(Integer a, String... strings){
        System.out.println(a);
        System.out.println(strings[0]);
    }
    public static void main(String[] args){
        CsvFileReadAndWrite csvFileReadAndWrite = new CsvFileReadAndWrite();
        List<Tweet> tweetList = csvFileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),"boredape.csv");
        Map<String, Integer> hashtagCount = new HashMap<>();
        String sinceday = "2021-08-01";
        String untilday = "2021-09-30";
        Pattern pattern = Pattern.compile("#\\w+");
        List<String> hashtagList = new ArrayList<>();
        for(Tweet tweet: tweetList){
            if(tweet.getTimeStamp().compareTo(untilday) > 0 || tweet.getTimeStamp().compareTo(sinceday) < 0)
                continue;
            Matcher matcher = pattern.matcher(tweet.getTweetText());
            while(matcher.find()){
//                System.out.println(matcher.group());
                if(hashtagCount.containsKey(matcher.group())){
                    hashtagCount.put(matcher.group(), hashtagCount.get(matcher.group()) + 1);
                }
                else{
                    hashtagCount.put(matcher.group(), 1);
                }
            }
        }
        ValueComparator bvc = new ValueComparator(hashtagCount);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        sorted_map.putAll(hashtagCount);
        int count = 0;
        for(Map.Entry<String, Integer> entry: sorted_map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
            count++;
            if(count == 10)
                break;
        }

    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}