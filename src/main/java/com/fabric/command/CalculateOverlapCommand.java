package com.fabric.command;

import com.fabric.DataStore;
import com.fabric.PortFolio;
import com.fabric.utils.FundOverlapCalculator;

import java.util.Set;

public class CalculateOverlapCommand implements Command {
    private PortFolio portFolio;
    private DataStore dataStore;

    public CalculateOverlapCommand(PortFolio portFolio, DataStore dataStore) {
        this.portFolio = portFolio;
        this.dataStore = dataStore;
    }

    public void Execute(String command) throws Exception {
        String fund = command.split(" ")[1];
        try {
            Set<String> requestedFundStocks = dataStore.getStocksFor(fund);
            for (String currentFund : this.portFolio.getFunds()) {
                Set<String> currentFundStock = dataStore.getStocksFor(currentFund);
                String overlapPercent = new FundOverlapCalculator().calculate(currentFundStock, requestedFundStocks);
                if (Double.parseDouble(overlapPercent) > (double) 0) {
                    System.out.println(fund + " " + currentFund + " " + overlapPercent + "%");
                }
            }

        } catch (Exception exception) {
            if (("FUND_NOT_FOUND").equals(exception.getMessage())) {
                System.out.println(exception.getMessage());
            }
            throw new Exception();
        }
    }
}
