package com.fabric;

import java.util.Set;

public interface DataStore {

    Set<String> getStocksFor(String mutualFund) throws Exception;

    Set<String> addStockFor(String mutualFund, String stockName) throws Exception;
}
