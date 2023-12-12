package org.group10.service;

import org.group10.model.nft.NFT;
import org.group10.repository.NftRepository;

import java.util.List;

public class NFTService {
    private NftRepository nftRepository = new NftRepository();

    public List<NFT> getAllNft(){
        return nftRepository.findAll();
    }

    public NFT getNftByName(String name){
        return nftRepository.getOneByName(name);
    }
}
