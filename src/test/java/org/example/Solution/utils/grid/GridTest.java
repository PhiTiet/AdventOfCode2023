package org.example.Solution.utils.grid;

import org.example.Solution.model.grid.Grid;
import org.example.Solution.model.grid.GridElement;
import org.example.Solution.utils.FileReader;
import org.junit.jupiter.api.Test;

import java.util.List;

class GridTest {
    private final FileReader fileReader = new FileReader();

    @Test
    void grid() {
        List<String> gridValues = fileReader.getLines("/day10.txt");
        var grid = new Grid<GridElement>(gridValues, GridElement.class);
        grid.print();
    }
}