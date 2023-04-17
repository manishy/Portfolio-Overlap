package com.fabric.command;

import com.fabric.PortFolio;

import java.util.Arrays;

public class CurrentPortfolioCommand implements Command {

    private PortFolio portFolio;

    public CurrentPortfolioCommand(PortFolio portFolio) {
        this.portFolio = portFolio;
    }

    @Override
    public void Execute(String command) {
        String[] splittedArgs = command.split(" ");
        String[] funds = Arrays.copyOfRange(splittedArgs, 1, splittedArgs.length);
        portFolio.addFunds(Arrays.asList(funds));
    }
}
