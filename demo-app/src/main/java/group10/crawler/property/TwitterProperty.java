package group10.crawler.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TwitterProperty  extends BaseProperty{
    private  String tweetProp = "//article[@data-testid='tweet']";
    private  String userNameProp = ".//div[@data-testid='User-Name']/div";
    private  String retweetProp = ".//div[@data-testid='retweet']";
    private  String likeProp = ".//div[@data-testid='like']";
    private  String replyProp = ".//div[@data-testid='reply']";
    private  String accountUrlProp = ".//span[contains(text(),'@')]";
    private  int MAX_SCROLL_ATTEMPTS = 3;
    private String username;
    private String password;
}
