package com.fabric;

import com.fabric.mocks.DummyDataStore;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandInvokerTest {

    @Test
    void shouldExecuteAGivenSetOfInstructions() throws Exception {
        PortFolio portFolio = new PortFolio();
        DummyDataStore dataStore = new DummyDataStore();
        CommandInvoker commandInvoker = new CommandInvoker(dataStore, portFolio, new Printer());
        commandInvoker.invoke("CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP UTI_NIFTY_INDEX");
        assertEquals(portFolio.getFunds(), Arrays.asList("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"));
        commandInvoker.invoke("ADD_STOCK AXIS_BLUECHIP NOCIL");
        Set<String> paragParikhFlexiCapStocks = dataStore.getStocksFor("AXIS_BLUECHIP");
        assertEquals(Arrays.asList("INFOSYS LIMITED", "BHARTI AIRTEL LIMITED", "NOCIL"), paragParikhFlexiCapStocks.stream().collect(Collectors.toList()));
    }
}