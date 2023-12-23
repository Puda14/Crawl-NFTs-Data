package backend.service;

import backend.dto.twitter.HashtagCount;

import java.util.List;

public interface AnalystService {
    List<HashtagCount> getTrendingHashtag(String startDate, String endDate);
}
