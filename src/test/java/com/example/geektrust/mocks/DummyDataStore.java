package com.example.geektrust.mocks;

import com.example.geektrust.DataStore;
import com.example.geektrust.exception.FundNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class DummyDataStore implements DataStore {
    Map<String, Set<String>> stockMappings = new HashMap<String, Set<String>>() {
    };

    private void setupData() {
        stockMappings.put("MIRAE_ASSET_LARGE_CAP", Arrays.asList("HDFC", "BHARTI AIRTEL LIMITED", "NMDC LIMITED").stream().collect(Collectors.toSet()));
        stockMappings.put("AXIS_BLUECHIP", Arrays.asList("INFOSYS LIMITED", "BHARTI AIRTEL LIMITED").stream().collect(Collectors.toSet()));
        stockMappings.put("MIRAE_ASSET_EMERGING_BLUECHIP", Arrays.asList("IPCA LABORATORIES LIMITED", "BATA INDIA LIMITED").stream().collect(Collectors.toSet()));
        stockMappings.put("ICICI_PRU_BLUECHIP", Arrays.asList("INFOSYS LIMITED",
                "BHARTI AIRTEL LIMITED",
                "OIL & NATURAL GAS CORPORATION LIMITED").stream().collect(Collectors.toSet()));
        stockMappings.put("UTI_NIFTY_INDEX", Arrays.asList("BHARTI AIRTEL LIMITED",
                "EPL LIMITED").stream().collect(Collectors.toSet()));
    }


    public DummyDataStore() {
        setupData();
    }

    @Override
    public Set<String> getStocksFor(String mutualFund) throws FundNotFoundException {
        Set<String> stockList = this.stockMappings.get(mutualFund);
        if (Objects.isNull(stockList)) {
            throw new FundNotFoundException();
        }
        return stockList;
    }

    @Override
    public void addStockFor(String mutualFund, String stockName) throws Exception {
        Set<String> currentStocks = getStocksFor(mutualFund);
        currentStocks.add(stockName);
    }
}
