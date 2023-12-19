package org.group10.service.impl;

import org.group10.crawler.APICrawler;
import org.group10.crawler.impl.NoAuthApiCrawler;
import org.group10.crawler.impl.TwitterCrawler;
import org.group10.dto.nftpricefloorapi.JsonPriceHistory;
import org.group10.dto.nftpricefloorapi.NFTDetail;
import org.group10.model.post.Tweet;
import org.group10.utils.fileio.FileReadAndWrite;
import org.group10.utils.fileio.impl.JsonFileReadAndWrite;
import org.group10.model.nft.Detail;
import org.group10.model.nft.NFT;
import org.group10.service.CrawlService;
import org.group10.utils.mapper.NftDetailMapper;
import org.group10.utils.mapper.PriceHistoryMapper;

import java.util.ArrayList;
import java.util.List;

import static org.group10.env.FileProperty.nftFilePath;
import static org.group10.env.NftSlugList.slugList;

public class CrawlServiceImpl implements CrawlService {

    @Override
    public void nftCrawlByListOfNft(){
        APICrawler apiCrawler = new NoAuthApiCrawler();
        List<NFT> nftList = new ArrayList<>();
        for(String slug : slugList) {
            String apiUrl = "https://api-bff.nftpricefloor.com/projects/"+ slug + "/charts/all";
            String detailApi = "https://api-bff.nftpricefloor.com/projects/" + slug;
            NFT nft = new NFT();
            NFTDetail nftDetail = apiCrawler.getApiData(detailApi, NFTDetail.class);
            NftDetailMapper mapper = new NftDetailMapper();
            Detail detail = mapper.map(nftDetail);
            nft.setDetail(detail);
            JsonPriceHistory jsonObject = apiCrawler.getApiData(apiUrl, JsonPriceHistory.class);
            nft.setPriceHistoryList(PriceHistoryMapper.map(jsonObject));
            nftList.add(nft);
        }
        FileReadAndWrite<NFT> fileReadAndWrite = new JsonFileReadAndWrite<>();
        fileReadAndWrite.writeToFile(nftList,nftFilePath);
    }

    @Override
    public List<Tweet>  postCrawl(String keyword, String startDate, String endDate){
        TwitterCrawler twitterCrawler = new TwitterCrawler();
        List<Tweet> tweets = (List<Tweet>) twitterCrawler.getWebsiteData(keyword, startDate, endDate);
        return tweets;
    }
}
