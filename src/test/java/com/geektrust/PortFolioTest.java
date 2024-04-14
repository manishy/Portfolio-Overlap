package com.geektrust;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortFolioTest {
    @Test
    void shouldAddAndGetFunds() {
        PortFolio portFolio = new PortFolio();
        portFolio.addFunds(Arrays.asList("FUND1", "FUND2"));
        assertEquals(portFolio.getFunds(), Arrays.asList("FUND1", "FUND2"));
    }
}


