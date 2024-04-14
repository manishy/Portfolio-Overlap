package com.example.geektrust;

import com.example.geektrust.exception.FundNotFoundException;
import com.example.geektrust.models.FundDataStore;
import com.example.geektrust.models.MutualFund;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MutualFundDataStore implements DataStore {
    private final String sourceFile;
    private Map<String, Set<String>> mutualFundStockMap;

    MutualFundDataStore(String sourceFile) throws IOException {
        this.sourceFile = sourceFile;
        this.initialise();
    }

    private void initialise() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream(sourceFile);
        FundDataStore fundDataStore = mapper.readValue(resourceAsStream, FundDataStore.class);
        this.mutualFundStockMap = fundDataStore.getFunds().stream().collect(Collectors.toMap(MutualFund::getName, MutualFund::getStocks));
    }

    @Override
    public Set<String> getStocksFor(String mutualFund) throws FundNotFoundException {
        Set<String> stocks = this.mutualFundStockMap.get(mutualFund);
        if (Objects.isNull(stocks)) {
            throw new FundNotFoundException();
        }
        return stocks;
    }

    @Override
    public void addStockFor(String mutualFund, String stockName) throws Exception {
        Set<String> currentStocks = getStocksFor(mutualFund);
        currentStocks.add(stockName);
    }
}
