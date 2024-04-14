package com.example.geektrust.commands;

import com.example.geektrust.PortFolio;
import com.example.geektrust.command.CurrentPortfolioCommand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentPortfolioCommandTest {
    @Test
    void execute() {
        String command = "CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP UTI_NIFTY_INDEX";
        PortFolio portFolio = new PortFolio();
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand();
        currentPortfolioCommand.execute(command, portFolio);
        assertEquals(Arrays.asList("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"), portFolio.getFunds());
    }
}
