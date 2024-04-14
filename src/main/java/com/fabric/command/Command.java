package com.fabric.command;

import com.fabric.PortFolio;

public interface Command {
    void execute(String command, PortFolio portFolio) throws Exception;
}


