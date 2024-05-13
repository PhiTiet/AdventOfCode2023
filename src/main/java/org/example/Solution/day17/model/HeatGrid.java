package org.example.Solution.day17.model;

import org.example.Solution.model.grid.Grid;

import java.util.List;

public class HeatGrid extends Grid<HeatTile> {
    public HeatGrid(List<String> lines) {
        super(lines, HeatTile.class);
    }
}
