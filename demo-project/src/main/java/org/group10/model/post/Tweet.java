package org.group10.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tweet extends BasePost{
    String tweetText;
    Integer reply;
    Integer retweet;
    Integer like;

    public Tweet(String link, String account, String time, String tweetText, Integer reply, Integer retweet, Integer like) {
        super();
        this.tweetText = tweetText;
        this.reply = reply;
        this.retweet = retweet;
        this.like = like;
    }
}
