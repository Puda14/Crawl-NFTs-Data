package backend.service;

import backend.dto.nftpricefloorapi.TopNFT;
import backend.model.nft.NFT;
import backend.model.post.Tweet;

import java.util.List;

public interface CrawlService {

    List<NFT> nftCrawlByListOfNft();

    NFT crawlNftBySlugName(String slug, String path);

    List<TopNFT> crawlTopListNft(String param, String startDate, String endDate);
    List<Tweet> postCrawl(String keyword, String startDate, String endDate, String path);
}
