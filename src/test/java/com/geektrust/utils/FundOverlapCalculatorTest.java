package com.geektrust.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FundOverlapCalculatorTest {
    FundOverlapCalculator fundOverlapCalculator = new FundOverlapCalculator();

    @Test
    public void calculateOverlapShouldCalculateOverlapBetweenToFunds() {
        Set<String> stockList1 = Arrays.asList("STOCK1", "STOCK2", "STOCK3").stream().collect(Collectors.toSet());
        Set<String> stockList2 = Arrays.asList("STOCK4", "STOCK3", "STOCK5", "STOCK7").stream().collect(Collectors.toSet());
        String actualOverlap = fundOverlapCalculator.calculate(stockList1, stockList2);
        System.out.println(actualOverlap);
        assertEquals("28.57", actualOverlap);
    }
}