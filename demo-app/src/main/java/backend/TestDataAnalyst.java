package backend;

import backend.model.nft.PriceHistory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.nft.NFT;
import backend.model.post.Tweet;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TestDataAnalyst {

    //hàm đếm số post theo ngày
    public static int countZ(List<Tweet> tweets, String date){
        int count =0;
        for (Tweet tweet : tweets){
            if(tweet.getTimeStamp().contains(date)) count++;
        }
        return count;
    }

    public static Date toDate(String inputDateStr) {

        // Định dạng đối tượng SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        // Đặt múi giờ cho đối tượng SimpleDateFormat
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            // Chuyển đổi chuỗi thành đối tượng Date
            Date date = sdf.parse(inputDateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int countPostsInLastNDays(List<Tweet> tweetList, Date inputDate, int n) {
        int count = 0;
        long inputTime = inputDate.getTime();
        for (Tweet tweet : tweetList) {
            long tweetTime = toDate(tweet.getTimeStamp()).getTime();
            if (tweetTime >= inputTime - (n-1) * 24 * 60 * 60 * 1000 && tweetTime < inputTime) {
                count++;
            }
        }

        return count;
    }
    public static void main(String[] args) {
        System.out.println("test crawl nft");
        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        PostController postController = injector.getInstance(PostController.class);


        NFT nft = nftController.getByName("Bored Ape Yacht Club");
        List<PriceHistory> priceHistory = nft.getPriceHistoryList(); // lấy lịch sử giá
        System.out.println(priceHistory.get(5).getTimestamps());

        List<TweetPrice> tweetPriceList = new ArrayList<>();
        //lấy list tweet
        List<Tweet> tweetList = postController.getAllPost();
        for (PriceHistory entry : priceHistory) {
            if(entry.getTimestamps().before(toDate("2022-07-30T23:48:07.000Z"))) {
                Date timestamp = entry.getTimestamps();
                int numberOfPostsInLast3Days = countPostsInLastNDays(tweetList, timestamp, 3);
                TweetPrice tweetPrice = new TweetPrice();
                tweetPrice.setPrice(entry.getFloorUsd());
                tweetPrice.setTimestamp(entry.getTimestamps());
                tweetPrice.setTweetN((double) numberOfPostsInLast3Days);
                tweetPriceList.add(tweetPrice);
            }
        }
        for(TweetPrice tweetPrice : tweetPriceList) {
            System.out.println(tweetPrice.getTimestamp() + " : " + tweetPrice.getPrice() + " - " + tweetPrice.getTweetN());
        }

        List<Double> variableX = new ArrayList<>();
        List<Double> variableY = new ArrayList<>() ;
        for (TweetPrice tweetPrice : tweetPriceList){

            if(tweetPrice.getTweetN() > 1 && tweetPrice.getTimestamp().before(toDate("2022-07-01T03:48:07.000Z"))
                    &&tweetPrice.getTimestamp().after(toDate("2022-05-30T23:48:07.000Z"))) {
                System.out.println(tweetPrice);
                variableX.add(tweetPrice.getPrice());
                variableY.add(tweetPrice.getTweetN());
            }
        }
        // Tính hệ số tương quan Pearson
        double correlation = calculatePearsonCorrelation(variableX, variableY);

        // In ra kết quả
        System.out.println("Hệ số tương quan Pearson: " + correlation);
    }

    private static double calculatePearsonCorrelation(List<Double> variableX, List<Double> variableY) {
        int n = variableX.size();

        // Tính giá trị trung bình của X và Y
        double meanX = calculateMean(variableX);
        double meanY = calculateMean(variableY);

        // Tính tử số và mẫu số
        double numerator = 0.0;
        double denominatorX = 0.0;
        double denominatorY = 0.0;

        for (int i = 0; i < n; i++) {
            double diffX = variableX.get(i) - meanX;
            double diffY = variableY.get(i) - meanY;

            numerator += diffX * diffY;
            denominatorX += diffX * diffX;
            denominatorY += diffY * diffY;
        }

        // Tính hệ số tương quan Pearson
        double correlation = numerator / Math.sqrt(denominatorX * denominatorY);

        return correlation;
    }

    // Hàm tính giá trị trung bình của một danh sách số
    private static double calculateMean(List<Double> values) {
        double sum = 0.0;

        for (Double value : values) {
            sum += value;
        }

        return sum / values.size();
    }
}
