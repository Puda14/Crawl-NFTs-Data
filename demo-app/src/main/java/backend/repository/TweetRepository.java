package backend.repository;

import backend.model.post.Tweet;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet,String>{
    List<Tweet> findByKeyword(String keyword);
    List<Tweet> findByKeyWordOrAccount(String keyword);
}
