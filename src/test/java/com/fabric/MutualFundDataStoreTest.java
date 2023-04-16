package com.fabric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MutualFundDataStoreTest {
    MutualFundDataStore mutualFundDataStore;
    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        String sourceFileName = "stock_data.json";
        File srcFile = new File(Thread.currentThread().getContextClassLoader().getResource(sourceFileName).toURI());
        mutualFundDataStore = new MutualFundDataStore(srcFile);
    }
    @Test
    public void getStocksForShouldThrowExceptionIfStockNotFound() throws IOException, URISyntaxException {
        assertThrows(Exception.class, () -> {
            mutualFundDataStore.getStocksFor("SOME_INVALID_FUND");
        });
    }


    @Test
    void getStocksForShouldReturnStocksForAGivenFund() throws Exception {
        Set<String> stocks = mutualFundDataStore.getStocksFor("ICICI_PRU_NIFTY_NEXT_50_INDEX");
        assertEquals(1, stocks.size());
        assertEquals("INDRAPRASTHA GAS LIMITED", stocks.stream().collect(Collectors.toList()).get(0));
    }

    @Test
    void addStockForShouldAddStockToAFund() throws Exception {
        Set<String> updatedStocks = mutualFundDataStore.addStockFor("ICICI_PRU_NIFTY_NEXT_50_INDEX", "TEST STOCK");
        assertEquals(2,updatedStocks.size());
        assertEquals(Arrays.asList("INDRAPRASTHA GAS LIMITED", "TEST STOCK"),updatedStocks.stream().collect(Collectors.toList()));
    }

}