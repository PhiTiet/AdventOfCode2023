package org.example.Solution.day10.model;

import lombok.Getter;
import lombok.Setter;
import org.example.Solution.model.grid.GridElement;

@Getter
@Setter
public class GroundTile extends GridElement {
    private GroundType groundType;

    public GroundTile(long x, long y, String symbol) {
        super(x, y, symbol);
        groundType = GroundType.fromString(symbol);
    }
}
