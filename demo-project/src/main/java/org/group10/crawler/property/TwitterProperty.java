package org.group10.crawler.property;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@AllArgsConstructor
@Data
public class TwitterProperty {

    private String loginUrl;
    private String logoutUrl;
    private String usernameInputField;
    private String passwordInputField;
    private String emailInputField;
    private String nextButton;
    private String loginButton;
    private String logoutButton;
    private String reloadButton;
    private String noResult;


    public TwitterProperty() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/twitter.properties")) {
            properties.load(input);

            loginUrl = properties.getProperty("loginUrl");
            logoutUrl = properties.getProperty("logoutUrl");
            usernameInputField = properties.getProperty("usernameInputField");
            passwordInputField = properties.getProperty("passwordInputField");
            emailInputField = properties.getProperty("emailInputField");
            nextButton = properties.getProperty("nextButton");
            loginButton = properties.getProperty("loginButton");
            logoutButton = properties.getProperty("logoutButton");
            reloadButton = properties.getProperty("reloadButton");
            noResult = properties.getProperty("noTweetFound");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
