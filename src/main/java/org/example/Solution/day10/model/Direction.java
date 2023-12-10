package org.example.Solution.day10.model;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Direction getReverse(Direction d) {
        switch (d) {
            case EAST -> {
                return WEST;
            }
            case WEST -> {
                return EAST;
            }
            case NORTH -> {
                return SOUTH;
            }
            case SOUTH -> {
                return NORTH;
            }
        }
        throw new IllegalStateException();
    }
}
