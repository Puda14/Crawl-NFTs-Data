package org.group10.service.impl;

import org.group10.model.nft.NFT;
import org.group10.repository.NftRepository;
import org.group10.service.NftService;

import java.util.List;

public class NFTServiceImpl implements NftService {
    private NftRepository nftRepository = new NftRepository();

    @Override
    public List<NFT> getAllNft(){
        return nftRepository.findAll();
    }

    @Override
    public NFT getNftByName(String name){
        return nftRepository.getOneByName(name);
    }
}
