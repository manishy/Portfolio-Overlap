package com.example.geektrust.commands;

import com.example.geektrust.PortFolio;
import com.example.geektrust.Printer;
import com.example.geektrust.command.CalculateOverlapCommand;
import com.example.geektrust.command.CurrentPortfolioCommand;
import com.example.geektrust.mocks.DummyDataStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand();
        currentPortfolioCommand.execute(currentPortfolioInstruction, portFolio);
        String calculateOverlapInstruction = "CALCULATE_OVERLAP MIRAE_ASSET_LARGE_CAP";
        CalculateOverlapCommand calculateOverlapCommand = new CalculateOverlapCommand(dataStore, new Printer());
        calculateOverlapCommand.execute(calculateOverlapInstruction, portFolio);
        assertEquals("MIRAE_ASSET_LARGE_CAP AXIS_BLUECHIP 40.00%\n" +
                "MIRAE_ASSET_LARGE_CAP ICICI_PRU_BLUECHIP 33.33%\n" +
                "MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 40.00%\n", outputStreamCaptor.toString());
    }

    @Test
    public void shouldNotCalculateFundOverlapIfOverlapPercentageIsZeroOrLess() throws Exception {
        DummyDataStore dataStore = new DummyDataStore();
        PortFolio portFolio = new PortFolio();
        String currentPortfolioInstruction = "CURRENT_PORTFOLIO AXIS_BLUECHIP";
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand();
        currentPortfolioCommand.execute(currentPortfolioInstruction, portFolio);
        String calculateOverlapInstruction = "CALCULATE_OVERLAP MIRAE_ASSET_EMERGING_BLUECHIP";
        CalculateOverlapCommand calculateOverlapCommand = new CalculateOverlapCommand(dataStore, new Printer());
        calculateOverlapCommand.execute(calculateOverlapInstruction, portFolio);
        assertEquals("", outputStreamCaptor.toString());
    }

    @Test
    public void shouldLogErrorMessageIfRequestedFundNotFound() throws Exception {
        DummyDataStore dataStore = new DummyDataStore();
        PortFolio portFolio = new PortFolio();
        String currentPortfolioInstruction = "CURRENT_PORTFOLIO AXIS_BLUECHIP";
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand();
        currentPortfolioCommand.execute(currentPortfolioInstruction, portFolio);
        String calculateOverlapInstruction = "CALCULATE_OVERLAP INVALID_FUND";
        CalculateOverlapCommand calculateOverlapCommand = new CalculateOverlapCommand(dataStore, new Printer());
        calculateOverlapCommand.execute(calculateOverlapInstruction, portFolio);
        assertEquals("FUND_NOT_FOUND\n", outputStreamCaptor.toString());
    }
}
