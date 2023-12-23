package gui.controller.analyst;

import backend.ConfigModule;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.List;

public class AnalystTabController {

    Injector injector = Guice.createInjector(new ConfigModule());
    NFTController nftController = injector.getInstance(NFTController.class);
    PostController postController = injector.getInstance(PostController.class);


    List<Tweet> tweetList = postController.getAllPost();
    public void initialize() {
        System.out.println("NFT : " + nftController);
        System.out.println("Post : " + tweetList);

    }
}
