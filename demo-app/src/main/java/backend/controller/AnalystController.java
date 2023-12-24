package backend.controller;

import backend.dto.twitter.TweetPrice;
import backend.dto.twitter.HashtagCount;
import backend.service.AnalystService;
import com.google.inject.Inject;

import java.util.List;

import static backend.utils.validate.Validator.isValidDate;

public class AnalystController {
    private final AnalystService analystService;

    @Inject
    public AnalystController(AnalystService analystService) {
        this.analystService = analystService;
    }

    public List<HashtagCount> getTrendingHashtag(String startDate, String endDate){
        return analystService.getTrendingHashtag(startDate,endDate);
    }

    public List<TweetPrice> getTweetAndPriceByTime(String nftName, String startDate, String endDate) {
        if (isValidDate(startDate) && isValidDate(endDate)) {
            return analystService.getTweetAndPriceByTime(nftName, startDate, endDate);
        }
        return null;
    }
    public Double calculatePearsonCorrelation(List<TweetPrice> tweetPriceList, String startDate, String endDate) {
        if (isValidDate(startDate) && isValidDate(endDate)) {
            return analystService.PearsonCorrelationCalculate(tweetPriceList, startDate, endDate);
        }
        return null;
    }

}
