package org.example.Solution.day14.model;

import lombok.Getter;
import lombok.Setter;
import org.example.Solution.model.grid.GridElement;

import static org.example.Solution.day14.model.RockType.*;

@Getter
@Setter
public class Rock extends GridElement {
    private RockType rockType;

    public Rock(long x, long y, String symbol) {
        super(x,y,symbol);
        rockType = symbol.equals("O") ? ROLLING_ROCK : symbol.equals("#") ? STEADFAST_ROCK : EMPTY;
    }
}
