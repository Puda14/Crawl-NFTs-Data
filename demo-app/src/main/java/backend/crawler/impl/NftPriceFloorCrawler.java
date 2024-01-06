package backend.crawler.impl;

import backend.crawler.SeleniumCrawler;
import backend.crawler.property.NftPriceFloorProperty;
import backend.dto.nftpricefloorapi.TopNFT;
import com.google.inject.Inject;
import backend.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;



public class NftPriceFloorCrawler implements SeleniumCrawler<TopNFT,List<TopNFT>> {
    private final SeleniumConfig seleniumConfig;
    private final NftPriceFloorProperty nftPriceFloorProperty;

    @Inject
    public NftPriceFloorCrawler(SeleniumConfig seleniumConfig, NftPriceFloorProperty nftPriceFloorProperty) {
        this.seleniumConfig = seleniumConfig;
        this.nftPriceFloorProperty = nftPriceFloorProperty;
    }

    @Override
    public List<TopNFT> getWebsiteData(String keyword, String startDay, String endDay) {
        WebDriver driver = seleniumConfig.initBrowser();
        String timeParam = "24h";
        LocalDate inputEndDate = LocalDate.parse(endDay, DateTimeFormatter.ISO_DATE);

        LocalDate inputStartDate = LocalDate.parse(startDay, DateTimeFormatter.ISO_DATE);
        long daysDifference = ChronoUnit.DAYS.between(inputStartDate,inputEndDate);
        if(daysDifference > 30){
            timeParam = "90d";
        }else if(daysDifference > 7){
            timeParam = "30d";
        }else if (daysDifference > 1){
            timeParam = "7d";
        }else if(daysDifference == 0){
            timeParam = "24h";
        }
        driver.get(nftPriceFloorProperty.getUrl() +
                nftPriceFloorProperty.getUrlPath1() +
                keyword+nftPriceFloorProperty.getUrlPath2() +
                timeParam);
        List<WebElement> nameElements = driver.findElements(By.xpath(nftPriceFloorProperty.getNamePath()));
        int cnt = 1;
        List<TopNFT> topNFTList = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            String name = nameElement.getText();
            String slug = nameElement.getAttribute("href");
            String[] parts = slug.split("/");
            slug = parts[parts.length - 1];
            if(cnt < 31) topNFTList.add(new TopNFT(name,slug,cnt++));
            else break;
        }
        driver.quit();
        return topNFTList;
    }


}
