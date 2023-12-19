package org.group10.controller;

import com.google.inject.Inject;
import org.group10.model.post.Tweet;
import org.group10.service.CrawlService;

import java.util.List;

public class CrawlController {
    private final CrawlService crawlService;

    @Inject
    public CrawlController(CrawlService crawlService) {
        this.crawlService = crawlService;
    }

    public void crawlNftData(){
        crawlService.nftCrawlByListOfNft();
    }

    public List<Tweet> crawlTweetDataByKeyword(String keyword, String startDate, String endDate){
        return crawlService.postCrawl(keyword,startDate,endDate);
    }
}
