package backend.controller;

import backend.model.nft.NFT;
import com.google.inject.Inject;
import backend.model.post.Tweet;
import backend.service.CrawlService;

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
    public NFT crawlNftBySlug(String slug, String path){
        return crawlService.crawlNftBySlugName(slug, path);
    }

    public List<Tweet> crawlTweetDataByKeyword(String keyword, String startDate, String endDate, String path){
        return crawlService.postCrawl(keyword, startDate, endDate, path);
    }
}
