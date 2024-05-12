package org.example.Solution.utils.grid;

import org.example.Solution.model.grid.Grid;
import org.example.Solution.model.grid.GridElement;
import org.example.Solution.utils.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {
    private final FileReader fileReader = new FileReader();

    @Test
    void grid() {
        List<String> gridValues = fileReader.getLines("/day10.txt");
        var grid = new Grid<>(gridValues, GridElement.class);
        grid.print();
    }

    @Test
    void sortsCorrectly(){
        List<String> gridValues = fileReader.getLines("/day10.txt");
        var grid = new Grid<>(gridValues, GridElement.class);
        List<GridElement> elements = grid.getElements();
        Collections.shuffle(elements);
        Collections.sort(elements);
        assertThat(elements.get(0).getX() == 0 && elements.get(0).getY() == 0);
        assertThat(elements.get(1).getX() == 1 && elements.get(0).getY() == 0);
    }
}