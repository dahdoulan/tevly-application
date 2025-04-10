package org.group15.tveely.ffmpeg;

import java.util.ArrayList;
import java.util.List;

public class CommandBuilder {

    private final List<Command> inputCommands = new ArrayList<>();
    private final List<Command> globalCommands = new ArrayList<>();
    private final List<Command> outputCommands = new ArrayList<>();

    public CommandBuilder addInputCommand(Command command) {
        inputCommands.add(command);
        return this;
    }
    public CommandBuilder addGlobalCommand(Command command) {
        globalCommands.add(command);
        return this;
    }
    public CommandBuilder addOutputCommand(Command command) {
        outputCommands.add(command);
        return this;
    }
    public void build() {

    }
}