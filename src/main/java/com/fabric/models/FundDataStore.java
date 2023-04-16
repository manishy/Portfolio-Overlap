package com.fabric.models;

import java.util.ArrayList;

public class FundDataStore {
    private ArrayList<FundDetails> funds;

    public ArrayList<FundDetails> getFunds() {
        return funds;
    }

    public void setFunds(ArrayList<FundDetails> funds) {
        this.funds = funds;
    }
}
