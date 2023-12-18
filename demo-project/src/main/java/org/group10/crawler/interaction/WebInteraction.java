package org.group10.crawler.interaction;

import org.openqa.selenium.WebDriver;

public interface WebInteraction {
    void login(WebDriver driver, String username, String password);
    void logout(WebDriver driver);
    Double scrollDown(WebDriver driver);

}
