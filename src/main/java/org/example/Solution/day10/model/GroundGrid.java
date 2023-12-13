package org.example.Solution.day10.model;

import org.example.Solution.model.grid.Grid;

import java.util.List;

public class GroundGrid extends Grid<GroundTile> {
    public GroundGrid(List<String> lines) {
        super(lines, GroundTile.class);
    }

    public void filterGrid(List<GroundTile> selectedElements) {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int index = super.getIndex(x, y);
                GroundTile element = elements.get(index);

                if (!selectedElements.contains(element)) {
                    element.setSymbol(".");
                    element.setGroundType(GroundType.GROUND);
                }
            }
        }
    }

    public List<GroundTile> getEmptyGroundTiles() {
        return elements.stream().filter(a -> a.getGroundType().equals(GroundType.GROUND)).toList();
    }
}
