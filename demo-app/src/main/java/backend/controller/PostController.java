package backend.controller;

import com.google.inject.Inject;
import backend.model.post.Tweet;
import backend.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService;

    @Inject
    public PostController(PostService postService) {
        this.postService = postService;
    }
    public List<Tweet> getAllPost(){
        return postService.getAll();
    }

    public List<Tweet> getByKeyword(String keyword){
        return postService.getByKeyword(keyword);
    }
    public List<Tweet> getByKeywordOrAccount(String keyword){return postService.getByKeywordOrAccount(keyword);}
}
