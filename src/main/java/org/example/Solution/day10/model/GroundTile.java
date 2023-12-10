package org.example.Solution.day10.model;

import lombok.Getter;
import org.example.Solution.utils.grid.GridElement;

@Getter
public class GroundTile extends GridElement {
    private final GroundType groundType;

    public GroundTile(long x, long y, String symbol) {
        super(x, y, symbol);
        groundType = GroundType.fromString(symbol);
    }
}
