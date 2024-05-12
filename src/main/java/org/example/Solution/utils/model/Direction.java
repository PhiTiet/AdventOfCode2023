package org.example.Solution.utils.model;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Direction getReverse(Direction d) {
        return switch (d) {
            case EAST -> WEST;
            case WEST -> EAST;
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
        };
    }
}