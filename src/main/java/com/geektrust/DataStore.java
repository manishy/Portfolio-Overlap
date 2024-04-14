package com.geektrust;

import com.geektrust.exception.FundNotFoundException;

import java.util.Set;

public interface DataStore {

    Set<String> getStocksFor(String mutualFund) throws FundNotFoundException;

    void addStockFor(String mutualFund, String stockName) throws Exception;
}
