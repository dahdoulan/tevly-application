package org.group15.tveely.ffmpeg;

public class Command {
    private final String name;
    private final String value;

    public Command(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}