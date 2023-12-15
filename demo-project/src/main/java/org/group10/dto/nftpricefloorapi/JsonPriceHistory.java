package org.group10.dto.nftpricefloorapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonPriceHistory {
    private String slug;
    private String granularity;
    private List<Long> timestamps;
    private List<Double> floorEth;
    private List<Double> floorUsd;
    private List<Integer> salesCount;
    private List<Double> volumeEth;
    private List<Double> volumeUsd;

}
