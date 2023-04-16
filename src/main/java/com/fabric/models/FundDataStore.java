package com.fabric.models;

import java.util.ArrayList;

public class FundDataStore {
    private ArrayList<MutualFund> funds;

    public ArrayList<MutualFund> getFunds() {
        return funds;
    }

    public void setFunds(ArrayList<MutualFund> funds) {
        this.funds = funds;
    }
}
