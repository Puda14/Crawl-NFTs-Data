package org.group10.service;

import org.group10.crawler.APICrawler;
import org.group10.crawler.impl.FirstApiCrawler;
import org.group10.dto.JsonPriceHistory;
import org.group10.dto.NFTDetail;
import org.group10.fileio.FileReadAndWrite;
import org.group10.fileio.impl.JsonFileReadAndWrite;
import org.group10.model.nft.NFT;
import org.group10.utils.PriceHistoryMapper;

import java.util.ArrayList;
import java.util.List;

import static org.group10.env.NftSlugList.slugList;

public class CrawlService {


    public void nftCrawl(){
        APICrawler apiCrawler = new FirstApiCrawler();
        List<NFT> nftList = new ArrayList<>();
        for(String slug : slugList) {
            String apiUrl = "https://api-bff.nftpricefloor.com/projects/"+ slug + "/charts/all";
            String detailApi = "https://api-bff.nftpricefloor.com/projects/" + slug;
            NFT nft = new NFT();
            NFTDetail nftDetail = apiCrawler.getApiData(detailApi, NFTDetail.class);
            nft.setNftDetail(nftDetail);
            System.out.println(nft.getNftDetail());
            JsonPriceHistory jsonObject = apiCrawler.getApiData(apiUrl, JsonPriceHistory.class);
            PriceHistoryMapper priceHistoryMapper = new PriceHistoryMapper();
            nft.setPriceHistoryList(priceHistoryMapper.mapper(jsonObject));
            System.out.println(nft.getPriceHistoryList());
            nftList.add(nft);
        }
        FileReadAndWrite<NFT> fileReadAndWrite = new JsonFileReadAndWrite<>("nft-list.json");
        fileReadAndWrite.writeToFile(nftList);
    }

    public void postCrawl(){

    }
}
