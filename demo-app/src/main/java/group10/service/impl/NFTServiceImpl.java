package group10.service.impl;

import group10.model.nft.NFT;
import group10.model.nft.NFT;
import group10.repository.NftRepository;
import group10.service.NftService;

import java.util.List;

public class NFTServiceImpl implements NftService {
    private NftRepository nftRepository = new NftRepository();

    @Override
    public List<NFT> getAllNft(){
        return nftRepository.findAll();
    }

    public NFT getNftByName(String name){
        return nftRepository.getOneByName(name);
    }
}
