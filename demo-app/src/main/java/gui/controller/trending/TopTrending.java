package gui.controller.trending;

import backend.ConfigModule;
import backend.controller.AnalystController;
import backend.dto.twitter.HashtagCount;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import static backend.utils.validate.Validator.isValidDate;

/**
 * Controller class for handling interactions in the Hashtag Analysis GUI.
 */
public class TopTrending {

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    @FXML
    private Button getTopHashtagButton;

    @FXML
    private Button getTopNFTButton;

    @FXML
    private ComboBox nftComboBox;

    @FXML
    private TableView<HashtagCount> tableView;

    @FXML
    private TableColumn<HashtagCount, String> hashtagColumn;

    @FXML
    private TableColumn<HashtagCount, Integer> countColumn;

    @FXML
    private TableColumn<HashtagCount, Number> topHashtagColumn;

    @FXML
    private TableColumn<HashtagCount, Number> topKeywordColumn;

    @FXML
    private TableColumn<HashtagCount, String> nftColumn;

    @FXML
    private TableColumn<HashtagCount, Integer> countNftColumn;

    @FXML
    private TableColumn<HashtagCount, Number> topNftColumn;

    @FXML
    private Pagination paginationHashtagTableView;

    @FXML
    private Pagination paginationNftTableView;

    @FXML
    private String topNftTime;

    private final int ITEMS_PER_PAGE = 10;
    private ObservableList<HashtagCount> dataHashtag;

//    private ObservableList<...> dataNft;
    private int totalPageCount = 0;

    public void initialize() {
        getTopHashtagButton.setDisable(true);

        startDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        endDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputAndUpdateButtonState();
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle when a row is selected (if needed)
        });

        paginationHashtagTableView.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
            loadTableViewData(newValue.intValue());
        });

        getTopHashtagButton.setOnAction(event -> {

            // User input
            String startDate = startDateTextField.getText();
            String endDate = endDateTextField.getText();

            if (isValidInput(startDate, endDate)) {

                // Get top Hashtag
                Injector injector = Guice.createInjector(new ConfigModule());
                AnalystController analystController = injector.getInstance(AnalystController.class);
                dataHashtag = FXCollections.observableArrayList(analystController.getTrendingHashtag(startDate, endDate));

                // Data Test for pagination
                dataHashtag.addAll(new HashtagCount("hashtag1", 10),
                        new HashtagCount("hashtag2", 15),
                        new HashtagCount("hashtag3", 20),
                        new HashtagCount("hashtag4", 5),
                        new HashtagCount("hashtag5", 8),
                        new HashtagCount("hashtag6", 12),
                        new HashtagCount("hashtag7", 18),
                        new HashtagCount("hashtag8", 25),
                        new HashtagCount("hashtag9", 30));

                hashtagColumn.setCellValueFactory(new PropertyValueFactory<>("hashtag"));
                countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

                // Update topHashtagColumn value using an overall index
                topHashtagColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashtagCount, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(TableColumn.CellDataFeatures<HashtagCount, Number> param) {
                        return new ReadOnlyObjectWrapper<>(dataHashtag.indexOf(param.getValue()) + 1);
                    }
                });

                totalPageCount = (int) Math.ceil((double) dataHashtag.size() / ITEMS_PER_PAGE);
                paginationHashtagTableView.setPageCount(totalPageCount);

                // Init Screen
                paginationHashtagTableView.setCurrentPageIndex(0);
                loadTableViewData(0);
            }
        });

        nftComboBox.getItems().addAll("24h", "7days", "9days", "90days");

        getTopNFTButton.setDisable(true);
        nftComboBox.valueProperty().addListener((observableNFT, oldTopNftTime, newTopNftTime) -> {
            topNftTime = (String) newTopNftTime;
            getTopNFTButton.setDisable(false);
        });

        getTopNFTButton.setOnAction(event -> {

            // Get top NFT by topNftTime

            // Data test

        });
    }

    /**
     * Validates input in the date fields and updates the state of the analysis button accordingly.
     */
    private void validateInputAndUpdateButtonState() {
        String startDate = startDateTextField.getText();
        String endDate = endDateTextField.getText();

        if (isValidInput(startDate, endDate)) {
            getTopHashtagButton.setDisable(false);
        } else {
            getTopHashtagButton.setDisable(true);
        }
    }

    /**
     * Checks if the provided dates are valid.
     *
     * @param startDate The start date.
     * @param endDate   The end date.
     * @return True if both dates are valid; otherwise, false.
     */
    private boolean isValidInput(String startDate, String endDate) {
        return isValidDate(startDate) && isValidDate(endDate);
    }

    /**
     * Loads data into the table view based on the current page index.
     *
     * @param pageIndex The index of the current page.
     */
    private void loadTableViewData(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, dataHashtag.size());

        if (fromIndex < toIndex) {
            tableView.setItems(FXCollections.observableArrayList(dataHashtag.subList(fromIndex, toIndex)));
        } else {
            tableView.setItems(FXCollections.emptyObservableList());
        }
    }
}
