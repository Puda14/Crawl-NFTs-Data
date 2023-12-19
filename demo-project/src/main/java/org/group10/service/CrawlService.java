package org.group10.service;

import org.group10.model.post.Tweet;

import java.util.List;

public interface CrawlService {

    void nftCrawlByListOfNft();
    List<Tweet> postCrawl(String keyword, String startDate, String endDate);
}
