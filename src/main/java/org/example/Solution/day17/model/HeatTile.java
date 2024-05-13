package org.example.Solution.day17.model;

import lombok.Getter;
import org.example.Solution.model.grid.GridElement;
import org.example.Solution.utils.model.Direction;

import java.util.HashSet;
import java.util.Set;

@Getter
public class HeatTile extends GridElement {
    private final long heat;
    private Set<Direction> passedDirections;

    public HeatTile(long x, long y, String symbol) {
        super(x, y, symbol);
        heat = Long.parseLong(symbol);
        passedDirections = new HashSet<>();
    }
}
