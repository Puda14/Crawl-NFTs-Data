package backend.dto.twitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class TweetPrice {
    Date timestamp;
    Double tweetNumber;
    Double price;
    Double like;
}
