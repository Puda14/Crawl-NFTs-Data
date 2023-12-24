package gui.controller.analyst;

import backend.ConfigModule;
import backend.TweetPrice;
import backend.controller.NFTController;
import backend.controller.PostController;
import backend.model.nft.NFT;
import backend.model.nft.PriceHistory;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static backend.TestDataAnalyst.countPostsInLastNDays;
import static backend.TestDataAnalyst.toDate;
import static backend.env.NftSlugList.nftNames;


public class AnalystTabController {

    @FXML
    private ComboBox<String> nftComboBox;

    @FXML
    private Button analystButton ;

    private String nftName;

    private List<TweetPrice> tweetPriceList = new ArrayList<>();

    @FXML
    private PieChart pieChart;

    public void initialize() {

        Injector injector = Guice.createInjector(new ConfigModule());
        NFTController nftController = injector.getInstance(NFTController.class);
        PostController postController = injector.getInstance(PostController.class);

        nftComboBox.getItems().addAll(nftNames);

        nftComboBox.valueProperty().addListener((observableNFT, oldValueNFT, newValueNFT) -> {

            // Select nft
            nftName = newValueNFT;
            System.out.println("NFT selected: " + nftName);

            // Should be: "Bored Ape Yacht Club"
            NFT nft = nftController.getByName(nftName);

            // Price History
            List<PriceHistory> priceHistory = nft.getPriceHistoryList();
            System.out.println(priceHistory.get(5).getTimestamps());

            // Get list tweet
            List<Tweet> tweetList = postController.getAllPost();
            for (PriceHistory entry : priceHistory) {
                if (entry.getTimestamps().before(toDate("2022-07-30T23:48:07.000Z"))) {
                    Date timestamp = entry.getTimestamps();
                    int numberOfPostsInLast3Days = countPostsInLastNDays(tweetList, timestamp, 3);
                    TweetPrice tweetPrice = new TweetPrice();
                    tweetPrice.setPrice(entry.getFloorUsd());
                    tweetPrice.setTimestamp(entry.getTimestamps());
                    tweetPrice.setTweetN((double) numberOfPostsInLast3Days);
                    tweetPriceList.add(tweetPrice);
                }
            }
            System.out.println(tweetPriceList);


            List<Double> variableX = new ArrayList<>();
            List<Double> variableY = new ArrayList<>();
            for (TweetPrice tweetPrice : tweetPriceList) {
                if (tweetPrice.getPrice() == null) System.out.println(tweetPrice);
                variableX.add(tweetPrice.getPrice());
                variableY.add(tweetPrice.getTweetN());
            }

            // Calculate the Pearson correlation coefficient
            double correlation = calculatePearsonCorrelation(variableX, variableY);
            System.out.println("Pearson correlation coefficient: " + correlation);

            // PieChart
            pieChart.getData().clear();
            
            PieChart.Data slice1 = new PieChart.Data("Pearson correlation coefficient", correlation);

            pieChart.getData().add(slice1);

            pieChart.getData().forEach(data ->
                    data.nameProperty().bind(data.pieValueProperty().asString("%.1f%%"))
            );

        });

        analystButton.setOnAction(event -> {
            // Load the details FXML file and controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/analystLineChart.fxml"));
            Parent root;
            try {
                root = loader.load();

                DrawLineChartController chart = loader.getController();
                chart.drawChart(tweetPriceList);

                // Create a new scene and stage
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Analyst Chart");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    private static double calculatePearsonCorrelation(List<Double> variableX, List<Double> variableY) {
        int n = variableX.size();

        // Calculate the average value of X and Y
        double meanX = calculateMean(variableX);
        double meanY = calculateMean(variableY);

        // Calculate the numerator and denominator
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

        // Calculate the Pearson correlation coefficient
        double correlation = numerator / Math.sqrt(denominatorX * denominatorY);

        return correlation;
    }

    // Function that calculates the average value of a list of numbers
    private static double calculateMean(List<Double> values) {
        double sum = 0.0;

        for (Double value : values) {
            sum += value;
        }

        return sum / values.size();
    }
}
