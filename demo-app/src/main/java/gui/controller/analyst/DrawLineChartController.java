package gui.controller.analyst;

import backend.dto.twitter.TweetPrice;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class DrawLineChartController {

    @FXML
    private LineChart<String, Number> lineChartPrice;

    @FXML
    private LineChart<String, Number> lineChartPost;

    public void drawChart(List<TweetPrice> tweetPriceList){

        // Line Chart
        lineChartPrice.getData().clear();
        lineChartPost.getData().clear();

        int MAX = 1000;

        if (lineChartPrice != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            int index = 0;
            for (TweetPrice item : tweetPriceList) {
                if(item.getTimestamp() != null && ++index <= MAX){
                    series.getData().add(new XYChart.Data<>(item.getTimestamp().toString(), item.getPrice()));
                }
            }

            lineChartPrice.setTitle("Price History");
            lineChartPrice.getData().add(series);
        }


        if (lineChartPost != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            int index = 0;
            for (TweetPrice item : tweetPriceList) {
                if (item.getTimestamp() != null && ++index <= MAX) {
                    series.getData().add(new XYChart.Data<>(item.getTimestamp().toString(), item.getTweetNumber()));
                }
            }

            lineChartPost.setTitle("Tweet Number");
            lineChartPost.getData().add(series);
        }


    }
}
