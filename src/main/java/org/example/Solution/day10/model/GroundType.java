package org.example.Solution.day10.model;

import lombok.Getter;

import static org.example.Solution.day10.model.Direction.*;

@Getter
public enum GroundType {
    VERTICAL_PIPE("|"),
    HORIZONTAL_PIPE("-"),
    NORTH_EAST_PIPE("L"),
    NORTH_WEST_PIPE("J"),
    SOUTH_WEST_PIPE("7"),
    SOUTH_EAST_PIPE("F"),
    GROUND(".");

    private final String symbol;

    GroundType(String symbol) {
        this.symbol = symbol;
    }

    public static GroundType fromString(String symbol) {
        for (GroundType groundType : GroundType.values()) {
            if (groundType.symbol.equals(symbol)) {
                return groundType;
            }
        }
        throw new IllegalArgumentException("Invalid groundType symbol: " + symbol);
    }


    public static Direction exitDirection(Direction enterDirection, GroundType type) {
        switch (type) {
            case VERTICAL_PIPE:
                return getExitDirection(enterDirection, NORTH, SOUTH);
            case HORIZONTAL_PIPE:
                return getExitDirection(enterDirection, EAST, WEST);
            case NORTH_EAST_PIPE:
                return getExitDirection(enterDirection, NORTH, EAST);
            case NORTH_WEST_PIPE:
                return getExitDirection(enterDirection, NORTH, WEST);
            case SOUTH_WEST_PIPE:
                return getExitDirection(enterDirection, SOUTH, WEST);
            case SOUTH_EAST_PIPE:
                return getExitDirection(enterDirection, SOUTH, EAST);
        }
        throw new IllegalStateException("nani");
    }

    private static Direction getExitDirection(Direction enter, Direction legalDirectionOne, Direction legalDirectionTwo) {
        assert (enter.equals(legalDirectionOne) || enter.equals(legalDirectionTwo));
        return enter.equals(legalDirectionOne) ? legalDirectionTwo : legalDirectionOne;
    }
}


