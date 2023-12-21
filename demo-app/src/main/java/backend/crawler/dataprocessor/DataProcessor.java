package backend.crawler.dataprocessor;

import backend.crawler.property.DataProperty;
import org.openqa.selenium.WebElement;

public interface DataProcessor<T,S extends DataProperty> {
     T getElementData(WebElement element, S s );
}
