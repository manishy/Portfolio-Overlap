package com.geektrust.exception;

public class FundNotFoundException extends Exception {
    public FundNotFoundException() {
        super("FUND_NOT_FOUND");
    }
}
