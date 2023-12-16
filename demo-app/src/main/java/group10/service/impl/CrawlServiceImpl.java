package group10.service.impl;

import group10.crawler.APICrawler;
import group10.crawler.impl.NftApiCrawler;
import group10.dto.nftpricefloorapi.JsonPriceHistory;
import group10.dto.nftpricefloorapi.NFTDetail;
import group10.utils.fileio.FileReadAndWrite;
import group10.utils.fileio.impl.JsonFileReadAndWrite;
import group10.model.nft.Detail;
import group10.model.nft.NFT;
import group10.service.CrawlService;
import group10.utils.mapper.NftDetailMapper;
import group10.utils.mapper.PriceHistoryMapper;

import java.util.ArrayList;
import java.util.List;

import static group10.env.FileProperty.nftFilePath;
import static group10.env.NftSlugList.slugList;

public class CrawlServiceImpl implements CrawlService {

    @Override
    public void nftCrawlByListOfNft(){
        APICrawler apiCrawler = new NftApiCrawler();
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
        FileReadAndWrite<NFT> fileReadAndWrite = new JsonFileReadAndWrite<>(nftFilePath);
        fileReadAndWrite.writeToFile(nftList);
    }

    public void postCrawl(){

    }
}
