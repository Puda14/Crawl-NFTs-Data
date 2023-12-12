package org.group10.crawler;

import java.lang.reflect.Type;

public interface APICrawler extends BaseCrawler {

    <T> T getApiData(String api,Type type);
}
