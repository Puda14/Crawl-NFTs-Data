package group10.crawler.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseProperty {
    private String searchBox;
    private String scrollScript;
    private int scrollDelayMs;
    private String timeProp;
}
