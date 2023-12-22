package backend.service.impl;

import com.google.inject.Inject;
import backend.crawler.APICrawler;
import backend.crawler.SeleniumCrawler;
import backend.crawler.impl.NoAuthApiCrawler;
import backend.dto.nftpricefloorapi.JsonPriceHistory;
import backend.dto.nftpricefloorapi.NFTDetail;
import backend.model.post.Tweet;
import backend.utils.fileio.FileReadAndWrite;
import backend.utils.fileio.impl.CsvFileReadAndWrite;
import backend.utils.fileio.impl.JsonFileReadAndWrite;
import backend.model.nft.Detail;
import backend.model.nft.NFT;
import backend.service.CrawlService;
import backend.utils.mapper.NftDetailMapper;
import backend.utils.mapper.PriceHistoryMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static backend.env.FileProperty.nftFilePath;
import static backend.env.FileProperty.tweetFilePath;
import static backend.env.NftSlugList.slugList;

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
