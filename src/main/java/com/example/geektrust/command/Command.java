package com.example.geektrust.command;

import com.example.geektrust.PortFolio;

public interface Command {
    void execute(String command, PortFolio portFolio) throws Exception;
}


