package com.geektrust;

import com.geektrust.mocks.DummyDataStore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandInvokerTest {

    @Test
    void shouldExecuteAGivenSetOfInstructions() throws Exception {
        PortFolio portFolio = new PortFolio();
        DummyDataStore dataStore = new DummyDataStore();
        CommandInvoker commandInvoker = new CommandInvoker(dataStore, new Printer());
        commandInvoker.invoke(portFolio, "CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP UTI_NIFTY_INDEX");
        assertEquals(portFolio.getFunds(), Arrays.asList("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"));
        commandInvoker.invoke(portFolio, "ADD_STOCK AXIS_BLUECHIP NOCIL");
        Set<String> paragParikhFlexiCapStocks = dataStore.getStocksFor("AXIS_BLUECHIP");
        assertEquals(Arrays.asList("INFOSYS LIMITED", "BHARTI AIRTEL LIMITED", "NOCIL"), new ArrayList<>(paragParikhFlexiCapStocks));
    }
}