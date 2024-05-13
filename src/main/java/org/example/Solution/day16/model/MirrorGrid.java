package org.example.Solution.day16.model;

import org.example.Solution.model.grid.Grid;

import java.util.List;

public class MirrorGrid extends Grid<Mirror> {
    public MirrorGrid(List<String> lines) {
        super(lines, Mirror.class);
    }
}
