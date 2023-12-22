package backend.crawler.interaction;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public interface WebInteraction {
    void login(WebDriver driver, String username, String password, String email) throws NoSuchElementException, TimeoutException, StaleElementReferenceException;
    void logout(WebDriver driver) throws NoSuchElementException, TimeoutException, StaleElementReferenceException;
    Double scrollDown(WebDriver driver);

}
