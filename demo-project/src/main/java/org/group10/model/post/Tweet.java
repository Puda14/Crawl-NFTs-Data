package org.group10.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tweet extends BasePost{
    String account;
    String timeStamp;
    String content;
    Integer reply;
    Integer retweet;
    Integer like;
}
