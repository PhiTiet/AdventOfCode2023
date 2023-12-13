package org.example.Solution.day11.model;

import org.example.Solution.model.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class SpaceGrid extends Grid<Space> {
    public SpaceGrid(List<String> lines) {
        super(lines, Space.class);
    }

    public List<Long> doubleSpaceXIndices() {
        var longs = new ArrayList<Long>();
        for (long i = 0; i < gridSize; i++) {
            long rowIndex = i;
            var row = elements.stream().filter(a -> a.getX() == rowIndex).toList();
            if (row.stream().allMatch(a -> a.getSpaceType().equals(SpaceType.EMPTY))) {
                longs.add(rowIndex);
            }
        }
        return longs;
    }

    public List<Long> doubleSpaceYIndices() {
        var longs = new ArrayList<Long>();
        for (long i = 0; i < gridSize; i++) {
            long columnIndex = i;
            var column = elements.stream().filter(a -> a.getY() == columnIndex).toList();
            if (column.stream().allMatch(a -> a.getSpaceType().equals(SpaceType.EMPTY))) {
                longs.add(columnIndex);
            }
        }
        return longs;
    }
}
