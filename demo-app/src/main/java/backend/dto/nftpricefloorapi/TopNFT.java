package backend.dto.nftpricefloorapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopNFT {
    private String name;
    private String slug;
    private Integer top;
}
