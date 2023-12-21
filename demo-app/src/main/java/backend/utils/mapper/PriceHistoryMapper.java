package backend.utils.mapper;

import backend.dto.nftpricefloorapi.JsonPriceHistory;
import backend.model.nft.PriceHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PriceHistoryMapper {
    public static List<PriceHistory> map(JsonPriceHistory jsonPriceHistory){
        List<PriceHistory> priceHistoryList = new ArrayList<>();
        int dataSize = jsonPriceHistory.getTimestamps().size();
        for(int i = 0; i < dataSize; i++){
            PriceHistory priceHistory = new PriceHistory();
            priceHistory.setTimestamps(new Date(jsonPriceHistory.getTimestamps().get(i)));
            priceHistory.setFloorEth(jsonPriceHistory.getFloorEth().get(i));
            priceHistory.setFloorUsd(jsonPriceHistory.getFloorUsd().get(i));
            priceHistory.setVolumeEth(jsonPriceHistory.getVolumeEth().get(i));
            priceHistory.setVolumeUsd(jsonPriceHistory.getVolumeUsd().get(i));
            priceHistory.setSalesCount(jsonPriceHistory.getSalesCount().get(i));
            priceHistoryList.add(priceHistory);
        }

        return priceHistoryList;
    }

}
