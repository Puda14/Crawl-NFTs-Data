package org.group10.repository;

import com.google.gson.reflect.TypeToken;
import org.group10.utils.fileio.FileReadAndWrite;
import org.group10.utils.fileio.impl.JsonFileReadAndWrite;
import org.group10.model.nft.NFT;

import java.util.List;

import static org.group10.env.FileProperty.nftFilePath;

public class NftRepository implements BaseRepository<NFT,String>{

    FileReadAndWrite<NFT> fileReadAndWrite = new JsonFileReadAndWrite<>(nftFilePath);

    @Override
    public List<NFT> findAll() {
        List<NFT> nftList = fileReadAndWrite.readFromFile(new TypeToken<List<NFT>>(){}.getType());

        return nftList;
    }

    @Override
    public  List<NFT> saveAll(List<NFT> nfts) {
        fileReadAndWrite.writeToFile(nfts);
        return nfts;
    }


    public NFT getOneByName(String name) {
        List<NFT> nftList = fileReadAndWrite.readFromFile(new TypeToken<List<NFT>>(){}.getType());
        for(int i = 0; i < nftList.size(); i++){
            if(nftList.get(i).getDetail().getName().equals(name))
                return nftList.get(i);
        }
        return null;
    }


    public NFT getOneById(Integer id) {
        return null;
    }

}
