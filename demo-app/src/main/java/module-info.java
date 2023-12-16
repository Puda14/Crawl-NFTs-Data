module com.example.demoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.google.gson;
    requires selenium.api;
    requires selenium.chrome.driver;


    opens com.example.demoapp to javafx.fxml;
    exports com.example.demoapp;
    exports com.example.demoapp.controller;
    opens com.example.demoapp.controller to javafx.fxml;
    exports com.example.demoapp.controller.nft;
    opens com.example.demoapp.controller.nft to javafx.fxml;
    opens com.example.demoapp.controller.home to javafx.fxml;
    opens com.example.demoapp.controller.post to javafx.fxml;
    opens com.example.demoapp.controller.crawler to javafx.fxml;
    opens com.example.demoapp.controller.analyst to javafx.fxml;
    opens group10.dto.nftpricefloorapi to com.google.gson;
    opens group10.model.nft to com.google.gson;


}


