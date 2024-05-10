package org.example.Solution.day14.model;

import org.example.Solution.model.grid.Grid;

import java.util.List;

public class RockGrid extends Grid<Rock> {
    public RockGrid(List<String> lines) {
        super(lines, Rock.class);
    }

}
