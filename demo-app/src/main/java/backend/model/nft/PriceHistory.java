package backend.model.nft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceHistory {
    private Date timestamps;
    private Double floorEth;
    private Double floorUsd;
    private Integer salesCount;
    private Double volumeEth;
    private Double volumeUsd;
}
