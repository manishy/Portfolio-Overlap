package com.geektrust.command;

import com.geektrust.PortFolio;

public interface Command {
    void execute(String command, PortFolio portFolio) throws Exception;
}


