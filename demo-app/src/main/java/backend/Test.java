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
    public static void main(String[] args){
        CsvFileReadAndWrite csvFileReadAndWrite = new CsvFileReadAndWrite();
        List<Tweet> tweetList = csvFileReadAndWrite.readFromFile(new TypeToken<List<Tweet>>(){}.getType(),"bored11.csv");
        System.out.println(tweetList.get(1).getLink());
        System.out.println(tweetList.get(1).getAccount());
        csvFileReadAndWrite.writeToFile(tweetList,"test.csv");
    }
}
