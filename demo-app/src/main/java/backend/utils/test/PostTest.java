package backend.utils.test;

import backend.ConfigModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import backend.controller.CrawlController;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.post.Tweet;

import java.util.List;

public class PostTest {
    public static void main(String[] args) {
        System.out.println("test controller tweet");
        Injector injector = Guice.createInjector(new ConfigModule());
        PostController postController = injector.getInstance(PostController.class);
        List<Tweet> tweets = postController.getAllPost();
        System.out.println(tweets.get(0).getLink());
        System.out.println(tweets.get(0).getAccount());
        System.out.println("Search By keyword");
        System.out.println(postController.getByKeyword("meebits").size());

    }
}
