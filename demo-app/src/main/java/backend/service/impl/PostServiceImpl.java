package backend.service.impl;

import com.google.inject.Inject;
import backend.model.post.Tweet;
import backend.repository.TweetRepository;
import backend.service.PostService;

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
    @Override
    public List<Tweet> getByKeywordOrAccount(String keyword) {return tweetRepository.findByKeyWordOrAccount(keyword);}
}
