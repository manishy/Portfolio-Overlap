package com.fabric;

import java.util.Set;

public interface DataStore {

    Set<String> getStocksFor(String mutualFund) throws Exception;

    void addStockFor(String mutualFund, String stockName) throws Exception;
}
