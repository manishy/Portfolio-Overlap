package com.fabric.commands;

import com.fabric.mocks.DummyDataStore;
import com.fabric.PortFolio;
import com.fabric.Printer;
import com.fabric.command.CalculateOverlapCommand;
import com.fabric.command.CurrentPortfolioCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculateOverlapCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldCalculateFundOverlapWithKnownFund() throws Exception {
        DummyDataStore dataStore = new DummyDataStore();
        PortFolio portFolio = new PortFolio();
        String currentPortfolioInstruction = "CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP UTI_NIFTY_INDEX";
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(portFolio);
        currentPortfolioCommand.execute(currentPortfolioInstruction);
        String calculateOverlapInstruction = "CALCULATE_OVERLAP MIRAE_ASSET_LARGE_CAP";
        CalculateOverlapCommand calculateOverlapCommand = new CalculateOverlapCommand(portFolio, dataStore, new Printer());
        calculateOverlapCommand.execute(calculateOverlapInstruction);
        assertEquals("MIRAE_ASSET_LARGE_CAP AXIS_BLUECHIP 40.00%\n" +
                "MIRAE_ASSET_LARGE_CAP ICICI_PRU_BLUECHIP 33.33%\n" +
                "MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 40.00%\n", outputStreamCaptor.toString());
    }

    @Test
    public void shouldNotCalculateFundOverlapIfOverlapPercentageIsZeroOrLess() throws Exception {
        DummyDataStore dataStore = new DummyDataStore();
        PortFolio portFolio = new PortFolio();
        String currentPortfolioInstruction = "CURRENT_PORTFOLIO AXIS_BLUECHIP";
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(portFolio);
        currentPortfolioCommand.execute(currentPortfolioInstruction);
        String calculateOverlapInstruction = "CALCULATE_OVERLAP MIRAE_ASSET_EMERGING_BLUECHIP";
        CalculateOverlapCommand calculateOverlapCommand = new CalculateOverlapCommand(portFolio, dataStore, new Printer());
        calculateOverlapCommand.execute(calculateOverlapInstruction);
        assertEquals("", outputStreamCaptor.toString());
    }

    @Test
    public void shouldLogErrorAndThrowExceptionIfRequestedFundNotFound() {
        DummyDataStore dataStore = new DummyDataStore();
        PortFolio portFolio = new PortFolio();
        String currentPortfolioInstruction = "CURRENT_PORTFOLIO AXIS_BLUECHIP";
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(portFolio);
        currentPortfolioCommand.execute(currentPortfolioInstruction);
        String calculateOverlapInstruction = "CALCULATE_OVERLAP INVALID_FUND";
        CalculateOverlapCommand calculateOverlapCommand = new CalculateOverlapCommand(portFolio, dataStore, new Printer());
        assertThrows(Exception.class, () -> {
            calculateOverlapCommand.execute(calculateOverlapInstruction);
        });
        assertEquals("FUND_NOT_FOUND\n", outputStreamCaptor.toString());
    }
}
