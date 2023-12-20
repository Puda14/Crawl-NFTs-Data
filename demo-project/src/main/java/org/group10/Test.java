package org.group10;

import org.group10.model.post.Tweet;

public class Test {
    public static void printSt(Integer a, String... strings){
        System.out.println(a);
        System.out.println(strings[0]);
    }
    public static void main(String[] args){
        Tweet tweet = new Tweet("a", "a", "a", "a", 1,1,1);
        System.out.println(tweet);
        System.out.println(tweet.getAccount() + " " + tweet.getLink());
    }
}
