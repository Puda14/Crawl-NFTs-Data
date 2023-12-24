package backend.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RedditPost extends BasePost{
    private String text;
    private Integer voteUp;
    private Integer comment;
}
