package org.group10.dto.nftpricefloorapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Details {
    private String name;
    private String slug;
    private String blockchain;
    private String releaseDate;
    private int totalSupply;
    private List<SocialMedia> socialMedia;
    private String contract;
    private List<Marketplace> marketplaces;
}
