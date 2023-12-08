package org.example.Solution.day08.model;

public enum TraverseCommand {
    LEFT,
    RIGHT;

    public static TraverseCommand fromString(String fromString) {
        return fromString.equals("L") ? LEFT : RIGHT;
    }
}
