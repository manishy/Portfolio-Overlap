package com.fabric.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FundOverlapCalculator {
    public String calculate(Set<String> firstStock, Set<String> secondStock) {
        int totalStocksCount = firstStock.size() + secondStock.size();
        ArrayList<String> unionStockList = new ArrayList<>();
        unionStockList.addAll(firstStock);
        unionStockList.addAll(secondStock);
        HashSet<String> uniqueStockList = new HashSet<>(unionStockList);
        int commonStockCount = totalStocksCount - uniqueStockList.size();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMinimumFractionDigits(2);
        double overlap = (200 * (double) commonStockCount / totalStocksCount);
        return df.format(overlap);
    }
}
