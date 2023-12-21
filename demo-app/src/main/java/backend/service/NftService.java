package backend.service;

import backend.model.nft.NFT;

import java.util.List;

public interface NftService {
    List<NFT> getAllNft();
    NFT getNftByName(String name);
}
