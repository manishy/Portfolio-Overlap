package com.example.geektrust.models;

import java.util.Set;

public class MutualFund {
    public String name;
    public Set<String> stocks;
    public Set<String> getStocks() {
        return stocks;
    }
    public String getName() {
        return name;
    }
}
