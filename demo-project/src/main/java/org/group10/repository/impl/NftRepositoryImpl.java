package org.group10.repository.impl;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import org.group10.repository.BaseRepository;
import org.group10.repository.NftRepository;
import org.group10.utils.fileio.FileReadAndWrite;
import org.group10.model.nft.NFT;

import java.util.List;

import static org.group10.env.FileProperty.nftFilePath;

public class NftRepositoryImpl implements NftRepository {

    private final FileReadAndWrite<NFT> fileReadAndWrite ;
    @Inject
    public NftRepositoryImpl(FileReadAndWrite<NFT> fileReadAndWrite) {
        this.fileReadAndWrite = fileReadAndWrite;
    }

    @Override
    public List<NFT> findAll() {
        List<NFT> nftList = fileReadAndWrite.readFromFile(new TypeToken<List<NFT>>(){}.getType(),nftFilePath );

        return nftList;
    }

    @Override
    public  List<NFT> saveAll(List<NFT> nfts) {
        fileReadAndWrite.writeToFile(nfts,nftFilePath);
        return nfts;
    }

    @Override
    public NFT getOneByName(String name) {
        List<NFT> nftList = fileReadAndWrite.readFromFile(new TypeToken<List<NFT>>(){}.getType(),nftFilePath);
        for(int i = 0; i < nftList.size(); i++){
            if(nftList.get(i).getDetail().getName().equals(name))
                return nftList.get(i);
        }
        return null;
    }


}
