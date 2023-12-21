package org.group10.service.impl;

import com.google.inject.Inject;
import org.group10.crawler.APICrawler;
import org.group10.crawler.SeleniumCrawler;
import org.group10.crawler.impl.NoAuthApiCrawler;
import org.group10.dto.nftpricefloorapi.JsonPriceHistory;
import org.group10.dto.nftpricefloorapi.NFTDetail;
import org.group10.model.post.Tweet;
import org.group10.utils.fileio.FileReadAndWrite;
import org.group10.utils.fileio.impl.CsvFileReadAndWrite;
import org.group10.utils.fileio.impl.JsonFileReadAndWrite;
import org.group10.model.nft.Detail;
import org.group10.model.nft.NFT;
import org.group10.service.CrawlService;
import org.group10.utils.mapper.NftDetailMapper;
import org.group10.utils.mapper.PriceHistoryMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.group10.env.FileProperty.nftFilePath;
import static org.group10.env.FileProperty.tweetFilePath;
import static org.group10.env.NftSlugList.slugList;

public class CrawlServiceImpl implements CrawlService {

    private  final APICrawler apiCrawler;
    private final SeleniumCrawler seleniumCrawler;

    @Inject
    public CrawlServiceImpl(APICrawler apiCrawler, SeleniumCrawler seleniumCrawler) {
        this.apiCrawler = apiCrawler;
        this.seleniumCrawler = seleniumCrawler;
    }

    @Override
    public void nftCrawlByListOfNft(){
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
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String filePath = "nft";
        FileReadAndWrite<NFT> fileReadAndWrite = new JsonFileReadAndWrite<>();
        fileReadAndWrite.writeToFile(nftList,filePath + dtf.format(localDateTime) + ".json");
    }

    @Override
    public List<Tweet>  postCrawl(String keyword, String startDate, String endDate) {
        List<Tweet> tweets;

            tweets = seleniumCrawler.getWebsiteData(keyword, startDate, endDate);
            FileReadAndWrite<Tweet> fileReadAndWrite = new CsvFileReadAndWrite();
            fileReadAndWrite.writeToFile(tweets, tweetFilePath);
            return tweets;

    }

}
