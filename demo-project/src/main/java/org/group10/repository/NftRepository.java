package org.group10.repository;

import org.group10.model.nft.NFT;

import java.util.List;

public interface NftRepository extends BaseRepository<NFT, String> {
    NFT getOneByName(String name);
}
