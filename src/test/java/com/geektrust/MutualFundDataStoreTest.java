package com.geektrust;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        mutualFundDataStore = new MutualFundDataStore("stock_data.json");
    }
    @Test
    public void getStocksForShouldThrowExceptionIfStockNotFound() {
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
        mutualFundDataStore.addStockFor("ICICI_PRU_NIFTY_NEXT_50_INDEX", "TEST STOCK");
        Set<String> updatedStocks = mutualFundDataStore.getStocksFor("ICICI_PRU_NIFTY_NEXT_50_INDEX");
                assertEquals(2,updatedStocks.size());
        assertEquals(Arrays.asList("INDRAPRASTHA GAS LIMITED", "TEST STOCK"),updatedStocks.stream().collect(Collectors.toList()));
    }

}