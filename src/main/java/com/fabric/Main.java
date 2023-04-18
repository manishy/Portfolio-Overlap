package com.fabric;

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
            CommandExecuter commandExecuter = new CommandExecuter(mutualFundDataStore, new PortFolio(), new Printer());
            List<String> instructions = Files.readAllLines(inputFilePath);
            for (String instruction : instructions) commandExecuter.execute(instruction);
        } catch (Exception e) {
            System.out.println("SOMETHING_WENT_WRONG");
        }
    }
}