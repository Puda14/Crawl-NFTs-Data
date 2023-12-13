package org.group10.service;

import org.group10.model.nft.NFT;

import java.util.List;

public interface NftService {
    List<NFT> getAllNft();
    NFT getNftByName(String name);
}
