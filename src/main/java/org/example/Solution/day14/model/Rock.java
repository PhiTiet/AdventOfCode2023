package org.example.Solution.day14.model;

import org.example.Solution.model.grid.GridElement;

import static org.example.Solution.day14.model.RockType.*;

public class Rock extends GridElement {
    private final RockType rockType;

    public Rock(long x, long y, String symbol) {
        super(x,y,symbol);
        rockType = symbol.equals("O") ? ROLLING_ROCK : symbol.equals("#") ? STEADFAST_ROCK : EMPTY;
    }
}
