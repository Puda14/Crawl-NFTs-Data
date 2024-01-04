package gui.controller.analyst;

import backend.ConfigModule;
import backend.dto.twitter.TweetPrice;
import backend.controller.AnalystController;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import static backend.env.NftSlugList.nftNames;


public class AnalystTabController {

    @FXML
    private ComboBox<String> nftComboBox;

    @FXML
    private Button analystButton ;

    @FXML
    private Label pearsonLabel;

    private String nftName;

    private List<TweetPrice> tweetPriceList = new ArrayList<>();

    public void initialize() {

        Injector injector = Guice.createInjector(new ConfigModule());
        AnalystController analystController = injector.getInstance(AnalystController.class);

        nftComboBox.getItems().addAll(nftNames);

        nftComboBox.valueProperty().addListener((observableNFT, oldValueNFT, newValueNFT) -> {

            tweetPriceList.clear();

            // Select nft
            nftName = newValueNFT;
            System.out.println("NFT selected: " + nftName);

            tweetPriceList = analystController.getTweetAndPriceByTime(nftName,"2021-08-01", "2023-03-08");
            // Calculate the Pearson correlation coefficient
            double correlation = analystController.calculatePearsonCorrelation(tweetPriceList,"2021-08-01", "2023-03-08");
            System.out.println("Pearson correlation coefficient: " + correlation);

            BigDecimal bd = new BigDecimal(correlation);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            correlation = bd.doubleValue();
            pearsonLabel.setText("Pearson correlation coefficient: " + correlation);
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
}
