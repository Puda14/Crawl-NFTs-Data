package org.group10.service.impl;

import com.google.inject.Inject;
import org.group10.model.post.Tweet;
import org.group10.repository.TweetRepository;
import org.group10.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final TweetRepository tweetRepository;

    @Inject
    public PostServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }
    @Override
    public List<Tweet> getAll(){
        return tweetRepository.findAll();
    }
    @Override
    public List<Tweet> getByKeyword(String keyword){
        return tweetRepository.findByKeyword(keyword);
    }
}
