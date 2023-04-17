package com.fabric.commands;

import com.fabric.PortFolio;
import com.fabric.command.CurrentPortfolioCommand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentPortfolioCommandTest {
    @Test
    void execute() {
        String command = "CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP UTI_NIFTY_INDEX";
        PortFolio portFolio = new PortFolio();
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(portFolio);
        currentPortfolioCommand.Execute(command);
        assertEquals(Arrays.asList("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"), portFolio.getFunds());
    }
}
