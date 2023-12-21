package backend.model.nft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import backend.dto.nftpricefloorapi.Marketplace;
import backend.dto.nftpricefloorapi.SocialMedia;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Detail {
    private String name;
    private String slug;
    private String blockchain;
    private String releaseDate;
    private int totalSupply;
    private String contract;
    private List<SocialMedia> socialMedia;
    private List<Marketplace> marketplaces;
}
