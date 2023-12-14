package org.group10.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumAction {
    public static void login(WebDriver driver, String username, String password){
        WebElement emailField = driver.findElement(By.xpath("//input[@name='text']"));
        emailField.sendKeys(username);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
    }

}
