package com.example.geektrust;

import com.example.geektrust.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

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
    void mainShouldExecuteCommandsProvidedFromFile() {
        new Main().main(new String[]{"src/test/resources/test_input.txt"});
        assertEquals("FUND_NOT_FOUND\n" +
                "FUND_NOT_FOUND\n", outputStreamCaptor.toString());
    }

    @Test
    void mainShouldPrintErrorIfAnyExceptionWasThrown() {
        String testInputPath = "src/test/resources/invalid_file.txt";
        new Main().main(new String[]{testInputPath});
        assertEquals("SOMETHING_WENT_WRONG\n", outputStreamCaptor.toString());
    }
}