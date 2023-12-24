package backend.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Tweet extends BasePost{
   private String tweetText;
   private Integer reply;
   private Integer retweet;
   private Integer like;

    public Tweet(String account,String link, String time, String tweetText, Integer reply, Integer retweet, Integer like) {
        super(account,link,time);
        this.tweetText = tweetText;
        this.reply = reply;
        this.retweet = retweet;
        this.like = like;
    }
}
