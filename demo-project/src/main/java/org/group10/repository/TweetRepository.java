package org.group10.repository;

import org.group10.model.post.Tweet;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet,String>{
    List<Tweet> findByKeyword(String keyword);
}
