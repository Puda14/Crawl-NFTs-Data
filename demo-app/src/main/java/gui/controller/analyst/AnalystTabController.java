package gui.controller.analyst;

import backend.ConfigModule;
import backend.TweetPrice;
import backend.controller.AnalystController;
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
import java.util.List;
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
        AnalystController analystController = injector.getInstance(AnalystController.class);

        nftComboBox.getItems().addAll(nftNames);

        nftComboBox.valueProperty().addListener((observableNFT, oldValueNFT, newValueNFT) -> {

            tweetPriceList.clear();

            // Select nft
            nftName = newValueNFT;
            System.out.println("NFT selected: " + nftName);

            tweetPriceList = analystController.getTweetAndPriceByTime(nftName,"2021-08-01", "2022-07-30");
            System.out.println(tweetPriceList);
            // Calculate the Pearson correlation coefficient
            double correlation = analystController.calculatePearsonCorrelation(tweetPriceList,"2021-08-01", "2022-07-30");
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
                System.out.println("param: " + tweetPriceList);
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
}
