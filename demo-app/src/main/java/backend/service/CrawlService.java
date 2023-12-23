package backend.service;

import backend.model.nft.NFT;
import backend.model.post.Tweet;

import java.util.List;

public interface CrawlService {

    void nftCrawlByListOfNft();

    NFT crawlNftBySlugName(String slug);
    List<Tweet> postCrawl(String keyword, String startDate, String endDate);
}
