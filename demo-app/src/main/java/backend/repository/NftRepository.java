package backend.repository;

import backend.model.nft.NFT;

import java.util.List;

public interface NftRepository extends BaseRepository<NFT, String> {
    NFT getOneByName(String name);
}
