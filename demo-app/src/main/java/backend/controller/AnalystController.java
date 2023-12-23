package backend.controller;

import backend.dto.twitter.HashtagCount;
import backend.service.AnalystService;
import com.google.inject.Inject;

import java.util.List;

public class AnalystController {
    private final AnalystService analystService;

    @Inject
    public AnalystController(AnalystService analystService) {
        this.analystService = analystService;
    }

    public List<HashtagCount> getTrendingHashtag(String startDate, String endDate){
        return analystService.getTrendingHashtag(startDate,endDate);
    }

}
