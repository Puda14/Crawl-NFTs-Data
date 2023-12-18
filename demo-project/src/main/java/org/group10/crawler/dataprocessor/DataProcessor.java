package org.group10.crawler.dataprocessor;

import org.group10.crawler.property.DataProperty;
import org.openqa.selenium.WebElement;

public interface DataProcessor<T,S extends DataProperty> {
     T getElementData(WebElement element, S s );
}
