package org.group10;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.group10.config.SeleniumConfig;
import org.group10.crawler.APICrawler;
import org.group10.crawler.SeleniumCrawler;
import org.group10.crawler.dataprocessor.DataProcessor;
import org.group10.crawler.dataprocessor.TweetDataProcessor;
import org.group10.crawler.impl.NoAuthApiCrawler;
import org.group10.crawler.impl.TwitterCrawler;
import org.group10.crawler.interaction.selenium.TwitterInteraction;
import org.group10.crawler.property.SeleniumProperty;
import org.group10.crawler.property.TweetProperty;
import org.group10.crawler.property.TwitterProperty;
import org.group10.model.nft.NFT;
import org.group10.model.post.Tweet;
import org.group10.repository.NftRepository;
import org.group10.repository.TweetRepository;
import org.group10.repository.impl.NftRepositoryImpl;
import org.group10.repository.impl.TweetRepositoryImpl;
import org.group10.service.CrawlService;
import org.group10.service.NftService;
import org.group10.service.PostService;
import org.group10.service.impl.CrawlServiceImpl;
import org.group10.service.impl.NFTServiceImpl;
import org.group10.service.impl.PostServiceImpl;
import org.group10.utils.fileio.FileReadAndWrite;
import org.group10.utils.fileio.impl.CsvFileReadAndWrite;
import org.group10.utils.fileio.impl.JsonFileReadAndWrite;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NftRepository.class).to(NftRepositoryImpl.class);
        bind(TweetRepository.class).to(TweetRepositoryImpl.class);
        bind(NftService.class).to(NFTServiceImpl.class);
        bind(PostService.class).to(PostServiceImpl.class);
        bind(CrawlService.class).to(CrawlServiceImpl.class);
        bind(new TypeLiteral<FileReadAndWrite<NFT>>(){}).toInstance(new JsonFileReadAndWrite());
        bind(new TypeLiteral<FileReadAndWrite<Tweet>>(){}).toInstance(new CsvFileReadAndWrite());

        bind(SeleniumProperty.class).toInstance(new SeleniumProperty());
        bind(TwitterProperty.class).toInstance(new TwitterProperty());
        bind(TweetProperty.class).toInstance(new TweetProperty());
        bind(DataProcessor.class).to(TweetDataProcessor.class);
        bind(SeleniumCrawler.class).to(TwitterCrawler.class);
        bind(APICrawler.class).to(NoAuthApiCrawler.class);
        bind(TwitterInteraction.class).toInstance(new TwitterInteraction());

    }
}
