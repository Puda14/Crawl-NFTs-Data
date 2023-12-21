package backend.service;

import backend.model.post.Tweet;

import java.util.List;

public interface CrawlService {

    void nftCrawlByListOfNft();
    List<Tweet> postCrawl(String keyword, String startDate, String endDate);
}
