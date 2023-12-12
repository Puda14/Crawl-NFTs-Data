package org.group10.repository;

import org.group10.model.nft.NFT;

import java.util.List;

public class NftRepository implements BaseRepository<NFT,Integer>{

    @Override
    public List<NFT> findAll() {
        return null;
    }

    @Override
    public void saveAll(Iterable<NFT> entities) {

    }


    public NFT getOneByName(String name) {
        return null;
    }


    public NFT getOneById(Integer id) {
        return null;
    }

}
