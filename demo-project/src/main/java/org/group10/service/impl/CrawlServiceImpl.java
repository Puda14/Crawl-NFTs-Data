package org.group10.service.impl;

import org.group10.crawler.APICrawler;
import org.group10.crawler.impl.NftApiCrawler;
import org.group10.dto.JsonPriceHistory;
import org.group10.dto.NFTDetail;
import org.group10.fileio.FileReadAndWrite;
import org.group10.fileio.impl.JsonFileReadAndWrite;
import org.group10.model.nft.NFT;
import org.group10.service.CrawlService;
import org.group10.utils.PriceHistoryMapper;

import java.util.ArrayList;
import java.util.List;

import static org.group10.env.FileProperty.nftFilePath;
import static org.group10.env.NftSlugList.slugList;

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
            nft.setNftDetail(nftDetail);
            JsonPriceHistory jsonObject = apiCrawler.getApiData(apiUrl, JsonPriceHistory.class);
            PriceHistoryMapper priceHistoryMapper = new PriceHistoryMapper();
            nft.setPriceHistoryList(priceHistoryMapper.mapper(jsonObject));
            nftList.add(nft);
        }
        FileReadAndWrite<NFT> fileReadAndWrite = new JsonFileReadAndWrite<>(nftFilePath);
        fileReadAndWrite.writeToFile(nftList);
    }

    public void postCrawl(){

    }
}
