package com.example.geektrust.command;

import com.example.geektrust.DataStore;
import com.example.geektrust.PortFolio;
import com.example.geektrust.Printer;
import com.example.geektrust.exception.FundNotFoundException;
import com.example.geektrust.utils.FundOverlapCalculator;

import java.util.Set;

public class CalculateOverlapCommand implements Command {
    private final DataStore dataStore;
    private final Printer printer;

    public CalculateOverlapCommand(DataStore dataStore, Printer printer) {
        this.dataStore = dataStore;
        this.printer = printer;
    }

    public void execute(String command, PortFolio portFolio) {
        String fund = command.split(" ")[1];
        try {
            Set<String> requestedFundStocks = dataStore.getStocksFor(fund);
            for (String currentFund : portFolio.getFunds()) {
                Set<String> currentFundStock = dataStore.getStocksFor(currentFund);
                String overlapPercent = new FundOverlapCalculator().calculate(currentFundStock, requestedFundStocks);
                if (Double.parseDouble(overlapPercent) > (double) 0) {
                    printer.print(fund + " " + currentFund + " " + overlapPercent + "%");
                }
            }

        } catch (FundNotFoundException exception) {
            printer.print(exception.getMessage());
        }
    }
}
