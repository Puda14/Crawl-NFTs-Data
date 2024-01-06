package gui.controller.analyst;

import backend.dto.twitter.TweetPrice;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SimpleTimeZone;

public class DrawLineChartController {

    @FXML
    private LineChart<String, Number> lineChartPrice;

    @FXML
    private LineChart<String, Number> lineChartPost;
    @FXML
    private ScrollPane scrollPaneForPrice;
    @FXML
    private ScrollPane scrollPaneForPost;

    public void drawChart(List<TweetPrice> tweetPriceList){

        // Line Chart
        lineChartPrice.getData().clear();
        lineChartPost.getData().clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int MAX = 1000;

        if (lineChartPrice != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            int index = 0;
            for (TweetPrice item : tweetPriceList) {
                if(item.getTimestamp() != null && ++index <= MAX){
                    String strDate = sdf.format(item.getTimestamp());
                    series.getData().add(new XYChart.Data<>(strDate, item.getPrice()));
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
                    String strDate = sdf.format(item.getTimestamp());
                    series.getData().add(new XYChart.Data<>(strDate, item.getTweetNumber()));
                }
            }

            lineChartPost.setTitle("Tweet Number");
            lineChartPost.getData().add(series);
        }


    }
}
