package com.fabric;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MutualFundDataStoreTest {
    @Test
    public void getStocksForShouldThrowExceptionIfStockNotFound() throws IOException, URISyntaxException {
        String sourceFileName = "stock_data.json";
        File srcFile = new File(Thread.currentThread().getContextClassLoader().getResource(sourceFileName).toURI());
        // Need to pass some dummy file
        MutualFundDataStore mutualFundDataStore = new MutualFundDataStore(srcFile);
        assertThrows(Exception.class, () -> {
            mutualFundDataStore.getStocksFor("SOME_INVALID_FUND");
        });
    }
}