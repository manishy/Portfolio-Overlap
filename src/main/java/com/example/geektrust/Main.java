package com.example.geektrust;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String dataSourceFileName = "stock_data.json";
        try {
            Path inputFilePath = Paths.get(args[0]);
            MutualFundDataStore mutualFundDataStore = new MutualFundDataStore(dataSourceFileName);
            CommandInvoker commandInvoker = new CommandInvoker(mutualFundDataStore, new Printer());
            List<String> instructions = Files.readAllLines(inputFilePath);
            PortFolio portFolio = new PortFolio();
            for (String instruction : instructions) commandInvoker.invoke(portFolio, instruction);
        } catch (Exception e) {
            System.out.println("SOMETHING_WENT_WRONG");
        }
    }
}