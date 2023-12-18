package org.group10.crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface SeleniumCrawler<T, S extends Iterable<T>> {
    Iterable<T> getWebsiteData(String keyword, String startDay, String endDay);
}
