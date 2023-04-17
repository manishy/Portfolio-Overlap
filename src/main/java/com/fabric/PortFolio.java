package com.fabric;

import java.util.ArrayList;
import java.util.List;

public class PortFolio {
    List<String> currentFunds = new ArrayList<String>() {
    };

    public void addFunds(List<String> funds) {
        currentFunds.addAll(funds);
    }

    public List<String> getFunds(){
        return currentFunds;
    }
}
