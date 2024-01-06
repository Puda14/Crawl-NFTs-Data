package backend.controller;

import backend.dto.nftpricefloorapi.TopNFT;
import backend.model.nft.NFT;
import backend.service.CrawlService;
import com.google.inject.Inject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static backend.env.NftSlugList.slugList;

public class UpdateController {
    private final CrawlService crawlService;

    @Inject
    public UpdateController(CrawlService crawlService) {
        this.crawlService = crawlService;
    }

    public List<NFT> updateNftData(){
        LocalDate today = LocalDate.now();
        LocalDate dateBefore31Days = today.minusDays(31);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedToday = today.format(formatter);
        String formattedStartDate = dateBefore31Days.format(formatter);
        List<TopNFT> topNFTList = crawlService.crawlTopListNft("volume",formattedStartDate,formattedToday);
        System.out.println(topNFTList);
        slugList.clear();
        for (TopNFT topNFT : topNFTList){
            slugList.add(topNFT.getSlug());
        }
        return crawlService.nftCrawlByListOfNft();

    }
}
