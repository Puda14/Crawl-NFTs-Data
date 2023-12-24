package backend.service;

import backend.model.post.Tweet;

import java.util.List;

public interface PostService {
    List<Tweet> getAll();
    List<Tweet> getByKeyword(String keyword);
    List<Tweet> getByKeywordOrAccount(String keyword);

}
