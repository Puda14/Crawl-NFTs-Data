package org.group10.controller;

import org.group10.model.nft.NFT;
import org.group10.service.NFTService;

import java.util.List;

public class NFTController {
    NFTService nftService = new NFTService();
    public List<NFT> getAll(){
        return nftService.getAllNft();
    }

    public NFT getByName(String name){
        return nftService.getNftByName(name);
    }
}
