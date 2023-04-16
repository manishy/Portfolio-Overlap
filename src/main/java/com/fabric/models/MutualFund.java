package com.fabric.models;

import java.util.Set;

public class MutualFund {
    public String name;
    public Set<String> stocks;
    public Set<String> getStocks() {
        return stocks;
    }

    public void setStocks(Set<String> stocks) {
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
