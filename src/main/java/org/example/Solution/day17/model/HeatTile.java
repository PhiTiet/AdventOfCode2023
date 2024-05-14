package org.example.Solution.day17.model;

import lombok.Getter;
import lombok.Setter;
import org.example.Solution.model.grid.GridElement;
import org.example.Solution.utils.model.Direction;

@Getter
@Setter
public class HeatTile extends GridElement {
    private final long heat;
    private PassedRecords passedRecords = new PassedRecords();

    public HeatTile(long x, long y, String symbol) {
        super(x, y, symbol);
        heat = Long.parseLong(symbol);
    }
}
