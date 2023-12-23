package gui.controller.crawler;

import backend.ConfigModule;
import backend.controller.CrawlController;
import backend.model.post.Tweet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static backend.utils.validate.Validator.isValidDate;
import static backend.utils.validate.Validator.isValidKeyword;


public class CrawlerTabController {
    @FXML
    private Button crawlButton;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    private boolean isCrawling = false;

    public void initialize() {
        // Thêm sự kiện người dùng nhập liệu cho các TextField
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        startDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        endDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        // Xử lý sự kiện khi nhấn nút "crawl"
        crawlButton.setOnAction(event -> {
            if (isCrawling) {
                crawlButton.setDisable(true);
                // Nếu đang crawl, không làm gì cả
                return;
            }

            // Bắt đầu quá trình crawl, đặt trạng thái là true
            isCrawling = true;

            // Lấy dữ liệu từ các trường nhập
            String keyword = keywordTextField.getText();
            String startDate = startDateTextField.getText();
            String endDate = endDateTextField.getText();

            // Validate dữ liệu
            if (isValidInput(keyword, startDate, endDate)) {
                // Dữ liệu hợp lệ, tiếp tục quá trình crawl
                System.out.println("crawl info: " + keyword + startDate + endDate);

                Injector injector = Guice.createInjector(new ConfigModule());
                CrawlController crawlController = injector.getInstance(CrawlController.class);
                CompletableFuture<List<Tweet>> crawlFuture = CompletableFuture.supplyAsync(() ->
                        crawlController.crawlTweetDataByKeyword(keyword, startDate, endDate));

                crawlFuture.thenAccept(tweets -> {
                    System.out.println(tweets);

                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Crawl complete");
                        alert.showAndWait();

                        // Crawl hoàn thành, đặt trạng thái là false và kích hoạt lại các thành phần
                        isCrawling = false;
                        enableInputFieldsAndButton();
                    });
                });
            } else {
                // Hiển thị thông báo lỗi nếu dữ liệu không hợp lệ
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập đầy đủ thông tin và định dạng ngày tháng là yyyy-mm-dd.");
                alert.showAndWait();

                // Crawl không thành công, đặt trạng thái là false và kích hoạt lại các thành phần
                isCrawling = false;
                enableInputFieldsAndButton();
            }
        });

        // Ban đầu, khi không có dữ liệu, disable nút crawl
        crawlButton.setDisable(true);
    }

    private void validateInputAndUpdateButtonState() {
        // Thực hiện kiểm tra ngay sau khi người dùng nhập liệu
        String keyword = keywordTextField.getText();
        String startDate = startDateTextField.getText();
        String endDate = endDateTextField.getText();

        // Validate dữ liệu và cập nhật trạng thái nút crawl
        if (isValidInput(keyword, startDate, endDate)) {
            // Dữ liệu hợp lệ, enable nút crawl
            crawlButton.setDisable(false);
        } else {
            // Dữ liệu không hợp lệ, disable nút crawl
            crawlButton.setDisable(true);
        }
    }

    private boolean isValidInput(String keyword, String startDate, String endDate) {
        // Thực hiện các kiểm tra để đảm bảo dữ liệu hợp lệ
        return isValidKeyword(keyword) && isValidDate(startDate) && isValidDate(endDate);
    }


    private void enableInputFieldsAndButton() {
        // Kích hoạt lại các trường nhập liệu và nút "crawl"
        keywordTextField.setDisable(false);
        startDateTextField.setDisable(false);
        endDateTextField.setDisable(false);
        crawlButton.setDisable(false);
    }

}
