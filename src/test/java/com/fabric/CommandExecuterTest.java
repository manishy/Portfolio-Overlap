package com.fabric;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandExecuterTest {

    @Test
    void shouldExecuteAGivenSetOfInstructions() throws Exception {
        PortFolio portFolio = new PortFolio();
        DummyDataStore dataStore = new DummyDataStore();
        CommandExecuter commandExecuter = new CommandExecuter(dataStore, portFolio);
        commandExecuter.execute("CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP UTI_NIFTY_INDEX");
        assertEquals(portFolio.getFunds(), Arrays.asList("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"));
        commandExecuter.execute("ADD_STOCK AXIS_BLUECHIP NOCIL");
        Set<String> paragParikhFlexiCapStocks = dataStore.getStocksFor("AXIS_BLUECHIP");
        assertEquals(Arrays.asList("INFOSYS LIMITED", "BHARTI AIRTEL LIMITED", "NOCIL"), paragParikhFlexiCapStocks.stream().collect(Collectors.toList()));
    }
}