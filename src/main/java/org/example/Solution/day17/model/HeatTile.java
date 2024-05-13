package org.example.Solution.day17.model;

import lombok.Getter;
import org.example.Solution.model.grid.GridElement;

@Getter
public class HeatTile extends GridElement {
    private final long heat;

    public HeatTile(long x, long y, String symbol) {
        super(x, y, symbol);
        heat = Long.parseLong(symbol);
    }
}
