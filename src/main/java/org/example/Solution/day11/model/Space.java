package org.example.Solution.day11.model;

import lombok.Getter;
import org.example.Solution.model.grid.GridElement;

@Getter
public class Space extends GridElement {
    private final SpaceType spaceType;

    public Space(long x, long y, String symbol) {
        super(x, y, symbol);
        spaceType = symbol.equals("#") ? SpaceType.GALAXY : SpaceType.EMPTY;
    }
}
