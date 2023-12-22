package org.group10.crawler.property;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@AllArgsConstructor
@Data
public class SeleniumProperty {
    private String driverType;
    private String driverPath;
    private String extensionPath;
    private String accountManagerPath;

    public SeleniumProperty() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/selenium.properties")) {
            properties.load(input);
            driverType = properties.getProperty("driverType");
            driverPath = properties.getProperty("driverPath");
            extensionPath = properties.getProperty("extensionPath");
            accountManagerPath = properties.getProperty("accountManagerPath");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
