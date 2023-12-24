module com.example.demoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.google.gson;
    requires selenium.chrome.driver;
    requires selenium.support;
    requires selenium.java;
    requires com.google.guice;
    requires org.apache.commons.csv;

    requires org.openqa.selenium.core;
    requires javafx.web;
    requires java.desktop;



    opens gui to javafx.fxml;
    exports gui;
    exports gui.controller;
    opens gui.controller to javafx.fxml;
    exports gui.controller.nft;
    opens gui.controller.nft to javafx.fxml;
    opens gui.controller.home to javafx.fxml;
    opens gui.controller.post to javafx.fxml;
    opens gui.controller.crawler to javafx.fxml;
    opens gui.controller.analyst to javafx.fxml;
    opens gui.controller.trending to javafx.fxml;
    opens backend.dto.nftpricefloorapi to com.google.gson;
    opens backend.model.nft to com.google.gson;
    opens backend.repository.impl to com.google.guice;
    opens backend.service.impl to com.google.guice;
    opens backend.crawler.impl to com.google.guice;
    opens backend.crawler.dataprocessor to com.google.guice;
    opens backend.crawler.interaction to com.google.guice;
    opens backend.crawler.property to com.google.guice;
    opens backend.controller to com.google.guice;
    opens backend.config to com.google.guice, selenium.chrome.driver;
    opens backend.crawler to com.google.guice;
    opens backend.dto.twitter to javafx.base;

}


