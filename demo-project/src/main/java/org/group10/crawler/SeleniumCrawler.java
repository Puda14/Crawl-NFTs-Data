package org.group10.crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface SeleniumCrawler<T, S extends Iterable<T>> {
    List<T> getWebsiteData(String keyword, String startDay, String endDay);
}
