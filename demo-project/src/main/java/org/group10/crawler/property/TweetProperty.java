package org.group10.crawler.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@AllArgsConstructor
@Data
public class TweetProperty extends DataProperty {
    private String tweetProp;
    private String linkProp;
    private String accountProp;
    private String timeProp;
    private String tweetTextProp;
    private String groupProp;

    public TweetProperty() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/twitter.properties")) {
            properties.load(input);

            // Assign values from properties
            tweetProp = properties.getProperty("tweetProp");
            linkProp = properties.getProperty("linkProp");
            accountProp = properties.getProperty("accountProp");
            timeProp = properties.getProperty("timeProp");
            tweetTextProp = properties.getProperty("tweetTextProp");
            groupProp = properties.getProperty("groupProp");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
