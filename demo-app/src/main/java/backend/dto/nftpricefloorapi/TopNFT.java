package backend.dto.nftpricefloorapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopNFT {
    String name;
    String slug;
    Integer top;
}
