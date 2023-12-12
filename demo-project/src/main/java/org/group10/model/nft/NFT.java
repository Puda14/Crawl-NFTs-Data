package org.group10.model.nft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group10.dto.NFTDetail;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NFT {
    private NFTDetail nftDetail;
    private List<PriceHistory> priceHistoryList;
}
