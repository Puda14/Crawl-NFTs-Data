package backend.utils.test;

import backend.model.post.Tweet;
import backend.utils.fileio.impl.CsvFileReadAndWrite;
import com.google.gson.reflect.TypeToken;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TreeMap;
import java.util.Comparator;

public class Test {
    public static void printSt(Integer a, String... strings){
        System.out.println(a);
        System.out.println(strings[0]);
    }
//    String normalWord[] = {"the", "to", "and", "of", "a", "in", "for", "is", "on", "that", "with", "I", "you", "s", "this", "my", "it", "are"};
    String normalWord[] = {"a", "an", "and", "as", "at", "be", "by", "for", "from", "has", "he", "I", "in", "is", "it", "its", "of", "on", "that", "the", "to", "was", "were", "will", "with", "you", "my", "this", "that",
        "are", "have", "has", "had", "do", "does", "did", "can", "could", "should", "all", "out", "up", "down", "no", "not", "so", "if", "or", "but", "what", "when", "where", "why", "how", "who", "whom", "which", "whose",
        "there", "their", "then", "than", "here", "when", "where", "why", "how", "who", "whom", "which", "whose", "there", "their", "then", "than", "here", "there", "these", "those", "because", "while", "about", "between",
        "into", "through", "during", "before", "after", "above", "below","just", "one", "your","more", "most", "other", "some", "such", "only", "same", "also", "very", "even", "now", "than", "get", "got", "gets"};

    public static void main(String[] args){
        CsvFileReadAndWrite csvFileReadAndWrite = new CsvFileReadAndWrite();
        List<Tweet> tweetList = csvFileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),"boredape.csv");
        Map<String, Integer> hashtagCount = new HashMap<>();
        String sinceday = "2021-08-01";
        String untilday = "2021-09-30";
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        List<String> wordList = new ArrayList<>();
        for(String word: new Test().normalWord){
            hashtagCount.put(word, -1);
//            System.out.println(word + " " + hashtagCount.get(word) + " " + hashtagCount.containsKey(word));
        }
        for(Tweet tweet: tweetList){
            if(tweet.getTimeStamp().compareTo(untilday) > 0 || tweet.getTimeStamp().compareTo(sinceday) < 0)
                continue;
            Matcher matcher = pattern.matcher(tweet.getTweetText());
            while(matcher.find()){
                if(matcher.group().length() < 3)
                    continue;
                String key = matcher.group().toLowerCase();
                if(Arrays.asList(new Test().normalWord).contains(key))
                    continue;
                if(hashtagCount.containsKey(key) && hashtagCount.get(key) != -1){
                    hashtagCount.put(key, hashtagCount.get(key) + 1);
                }
                else{
                    hashtagCount.put(key, 1);
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