package com.fabric;

import com.fabric.command.AddStockCommand;
import com.fabric.command.CalculateOverlapCommand;
import com.fabric.command.Command;
import com.fabric.command.CurrentPortfolioCommand;

import java.util.HashMap;


public class CommandInvoker {
    private final DataStore dataStore;
    private final Printer printer;
    HashMap<String, Command> commandsMap = new HashMap<>();

    public CommandInvoker(DataStore dataStore, Printer printer) {
        this.dataStore = dataStore;
        this.printer = printer;
        setUp();
    }

    private void setUp() {
        commandsMap.put("CURRENT_PORTFOLIO", new CurrentPortfolioCommand());
        commandsMap.put("CALCULATE_OVERLAP", new CalculateOverlapCommand(this.dataStore, this.printer));
        commandsMap.put("ADD_STOCK", new AddStockCommand(this.dataStore));
    }

    public void invoke(PortFolio portFolio, String instructions) throws Exception {
        String command = instructions.split(" ")[0];
        commandsMap.get(command).execute(instructions, portFolio);
    }
}
