package com.fabric.command;

import com.fabric.PortFolio;

import java.util.Arrays;

public class CurrentPortfolioCommand implements Command {
    @Override
    public void execute(String command, PortFolio portFolio) {
        String[] splittedArgs = command.split(" ");
        String[] funds = Arrays.copyOfRange(splittedArgs, 1, splittedArgs.length);
        portFolio.addFunds(Arrays.asList(funds));
    }
}
