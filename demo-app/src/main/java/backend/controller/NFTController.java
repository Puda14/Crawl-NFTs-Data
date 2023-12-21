package backend.controller;

import com.google.inject.Inject;
import backend.model.nft.NFT;
import backend.service.NftService;

import java.util.List;

public class NFTController {
    private final NftService nftService;

    @Inject
    public NFTController(NftService nftService) {
        this.nftService = nftService;
    }

    public List<NFT> getAll(){
        return nftService.getAllNft();
    }

    public NFT getByName(String name){
        return nftService.getNftByName(name);
    }
}
