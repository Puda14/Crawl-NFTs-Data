package org.group10.utils.mapper;

import org.group10.dto.nftpricefloorapi.NFTDetail;
import org.group10.model.nft.Detail;

public class NftDetailMapper implements ModelMapper{

    public Detail map(NFTDetail json) {
        Detail detail = new Detail();
        detail.setName(json.getDetails().getName());
        detail.setSlug(json.getDetails().getSlug());
        detail.setReleaseDate(json.getDetails().getReleaseDate());
        detail.setContract(json.getDetails().getContract());
        detail.setSocialMedia(json.getDetails().getSocialMedia());
        detail.setMarketplaces(json.getDetails().getMarketplaces());
        detail.setTotalSupply(json.getDetails().getTotalSupply());
        detail.setBlockchain(json.getDetails().getBlockchain());
        return detail;
    }

}
