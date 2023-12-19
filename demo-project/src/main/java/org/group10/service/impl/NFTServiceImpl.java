package org.group10.service.impl;

import com.google.inject.Inject;
import org.group10.model.nft.NFT;
import org.group10.repository.NftRepository;
import org.group10.repository.impl.NftRepositoryImpl;
import org.group10.service.NftService;

import java.util.List;

public class NFTServiceImpl implements NftService {
    private final NftRepository nftRepository;

    @Inject
    public NFTServiceImpl(NftRepository nftRepository) {
        this.nftRepository = nftRepository;
    }

    @Override
    public List<NFT> getAllNft(){
        return nftRepository.findAll();
    }

    @Override
    public NFT getNftByName(String name){
        return nftRepository.getOneByName(name);
    }
}
