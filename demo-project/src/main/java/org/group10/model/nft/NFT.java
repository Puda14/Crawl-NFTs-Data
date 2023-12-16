package org.group10.model.nft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NFT {
    private Detail detail;
    private List<PriceHistory> priceHistoryList;
}
