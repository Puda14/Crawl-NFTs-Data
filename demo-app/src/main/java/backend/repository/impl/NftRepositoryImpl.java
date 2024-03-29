package backend.repository.impl;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import backend.repository.NftRepository;
import backend.utils.fileio.FileReadAndWrite;
import backend.model.nft.NFT;

import java.util.List;

import static backend.env.FilePath.nftFilePath;

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
