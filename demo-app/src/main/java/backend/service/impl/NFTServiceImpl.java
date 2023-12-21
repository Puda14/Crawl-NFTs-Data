package backend.service.impl;

import com.google.inject.Inject;
import backend.model.nft.NFT;
import backend.repository.NftRepository;
import backend.repository.impl.NftRepositoryImpl;
import backend.service.NftService;

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
