package backend.crawler.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static backend.env.FilePath.nftPriceFloorPropFilePath;

@AllArgsConstructor
@Data
public class NftPriceFloorProperty {
    private String url;
    private String urlPath1;
    private String urlPath2;
    private String namePath;

    public NftPriceFloorProperty() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(nftPriceFloorPropFilePath)) {
            properties.load(input);
            url = properties.getProperty("url");
            urlPath1 = properties.getProperty("urlPath1");
            urlPath2 = properties.getProperty("urlPath2");
            namePath = properties.getProperty("namePath");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
