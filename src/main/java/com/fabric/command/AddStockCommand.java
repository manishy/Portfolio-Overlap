package com.fabric.command;

import com.fabric.DataStore;

import java.util.Arrays;
import java.util.Set;

public class AddStockCommand implements Command {
    private DataStore dataStore;

    public AddStockCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String command) throws Exception {
        String[] args = command.split(" ");
        String[] instruction = Arrays.copyOfRange(command.split(" "), 1, args.length);
        String fundName = instruction[0];
        String stockName = String.join(" ", Arrays.copyOfRange(instruction, 1, instruction.length));
        Set<String> currentFundStocks = dataStore.getStocksFor(fundName);
        currentFundStocks.add(stockName);
    }
}
