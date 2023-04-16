package com.fabric;

import com.fabric.models.FundDetails;
import com.fabric.models.FundDataStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MutualFundDataStore {
    private File sourceFile;
    private Map<String, Set<String>> mutualFundStockMap;

    MutualFundDataStore(File sourceFile) throws IOException {
        this.sourceFile = sourceFile;
        this.initialise();
    }

    private void initialise() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FundDataStore fundDataStore = mapper.readValue(sourceFile, FundDataStore.class);
        this.mutualFundStockMap = fundDataStore.getFunds().stream().collect(Collectors.toMap(FundDetails::getName, FundDetails::getStocks));
    }

    public Set<String> getStocksFor(String mutualFund) throws Exception {
        Set<String> stocks = this.mutualFundStockMap.get(mutualFund);
        if (stocks == null) {
            throw new Exception("FUND_NOT_FOUND");
        }
        return stocks;
    }
}
