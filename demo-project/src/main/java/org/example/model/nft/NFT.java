package org.example.model.nft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.NFTDetail;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NFT {
    private NFTDetail nftDetail;
    private List<PriceHistory> priceHistoryList;
}
