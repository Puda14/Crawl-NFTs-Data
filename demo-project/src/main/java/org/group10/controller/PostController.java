package org.group10.controller;

import com.google.inject.Inject;
import org.group10.model.post.Tweet;
import org.group10.service.PostService;

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
}
