package backend;

import backend.crawler.impl.NftPriceFloorCrawler;
import backend.crawler.property.NftPriceFloorProperty;
import backend.service.AnalystService;
import backend.service.impl.AnalystServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import backend.config.SeleniumConfig;
import backend.crawler.APICrawler;
import backend.crawler.SeleniumCrawler;
import backend.crawler.dataprocessor.DataProcessor;
import backend.crawler.dataprocessor.TweetDataProcessor;
import backend.crawler.impl.NoAuthApiCrawler;
import backend.crawler.impl.TwitterCrawler;
import backend.crawler.interaction.selenium.TwitterInteraction;
import backend.crawler.property.SeleniumProperty;
import backend.crawler.property.TweetProperty;
import backend.crawler.property.TwitterProperty;
import backend.model.nft.NFT;
import backend.model.post.Tweet;
import backend.repository.NftRepository;
import backend.repository.TweetRepository;
import backend.repository.impl.NftRepositoryImpl;
import backend.repository.impl.TweetRepositoryImpl;
import backend.service.CrawlService;
import backend.service.NftService;
import backend.service.PostService;
import backend.service.impl.CrawlServiceImpl;
import backend.service.impl.NFTServiceImpl;
import backend.service.impl.PostServiceImpl;
import backend.utils.fileio.FileReadAndWrite;
import backend.utils.fileio.impl.CsvFileReadAndWrite;
import backend.utils.fileio.impl.JsonFileReadAndWrite;
import com.google.inject.name.Names;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NftRepository.class).to(NftRepositoryImpl.class);
        bind(TweetRepository.class).to(TweetRepositoryImpl.class);
        bind(NftService.class).to(NFTServiceImpl.class);
        bind(PostService.class).to(PostServiceImpl.class);
        bind(CrawlService.class).to(CrawlServiceImpl.class);
        bind(AnalystService.class).to(AnalystServiceImpl.class);
        bind(new TypeLiteral<FileReadAndWrite<NFT>>(){}).toInstance(new JsonFileReadAndWrite());
        bind(new TypeLiteral<FileReadAndWrite<Tweet>>(){}).toInstance(new CsvFileReadAndWrite());

        bind(SeleniumProperty.class).toInstance(new SeleniumProperty());
        bind(TwitterProperty.class).toInstance(new TwitterProperty());
        bind(TweetProperty.class).toInstance(new TweetProperty());
        bind(NftPriceFloorProperty.class).toInstance(new NftPriceFloorProperty());
        bind(DataProcessor.class).to(TweetDataProcessor.class);
        bind(SeleniumCrawler.class).annotatedWith(Names.named("NFTCrawler")).to(NftPriceFloorCrawler.class); // Thay thế với triển khai thực tế của bạn
        bind(SeleniumCrawler.class).annotatedWith(Names.named("TwitterCrawler")).to(TwitterCrawler.class); // Thay thế với triển khai thực tế của bạn
        bind(APICrawler.class).to(NoAuthApiCrawler.class);
        bind(TwitterInteraction.class).toInstance(new TwitterInteraction());


    }
}
