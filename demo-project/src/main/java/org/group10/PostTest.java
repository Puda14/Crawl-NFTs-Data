package org.group10;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.group10.controller.CrawlController;
import org.group10.controller.NFTController;
import org.group10.controller.PostController;
import org.group10.model.post.Tweet;

import java.util.List;

public class PostTest {
    public static void main(String[] args) {
        System.out.println("test controller tweet");
        Injector injector = Guice.createInjector(new ConfigModule());
        PostController postController = injector.getInstance(PostController.class);
        List<Tweet> tweets = postController.getAllPost();
        System.out.println(tweets);
        System.out.println("Search By keyword");
        System.out.println(postController.getByKeyword("nft").size());

    }
}
