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
    public static Direction getTurnRight(Direction d){
        return switch (d) {
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case NORTH -> EAST;
            case SOUTH -> WEST;
        };
    }
    public static Direction getTurnLeft(Direction d){
        return switch (d) {
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case NORTH -> WEST;
            case SOUTH -> EAST;
        };
    }
}