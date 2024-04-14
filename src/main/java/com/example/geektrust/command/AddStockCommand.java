package com.example.geektrust.command;

import com.example.geektrust.DataStore;
import com.example.geektrust.PortFolio;

import java.util.Arrays;
import java.util.Set;

public class AddStockCommand implements Command {
    private final DataStore dataStore;

    public AddStockCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String command, PortFolio portFolio) throws Exception {
        String[] args = command.split(" ");
        String[] instruction = Arrays.copyOfRange(command.split(" "), 1, args.length);
        String fundName = instruction[0];
        String stockName = String.join(" ", Arrays.copyOfRange(instruction, 1, instruction.length));
        Set<String> currentFundStocks = dataStore.getStocksFor(fundName);
        currentFundStocks.add(stockName);
    }
}
