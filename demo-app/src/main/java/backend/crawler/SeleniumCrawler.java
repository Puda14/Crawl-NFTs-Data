package backend.crawler;

import java.util.List;

public interface SeleniumCrawler<T, S extends Iterable<T>> extends BaseCrawler {
    List<T> getWebsiteData(String keyword, String startDay, String endDay);
}
