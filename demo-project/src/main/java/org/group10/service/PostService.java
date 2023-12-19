package org.group10.service;

import org.group10.model.post.Tweet;

import java.util.List;

public interface PostService {
    List<Tweet> getAll();
    List<Tweet> getByKeyword(String keyword);
}
