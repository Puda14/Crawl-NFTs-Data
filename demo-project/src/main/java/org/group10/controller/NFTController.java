package org.group10.controller;

import org.group10.model.nft.NFT;
import org.group10.service.impl.NFTServiceImpl;

import java.util.List;

public class NFTController {
    NFTServiceImpl nftServiceImpl = new NFTServiceImpl();
    public List<NFT> getAll(){
        return nftServiceImpl.getAllNft();
    }

    public NFT getByName(String name){
        return nftServiceImpl.getNftByName(name);
    }
}
