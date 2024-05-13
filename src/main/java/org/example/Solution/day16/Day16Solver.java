package org.example.Solution.day16;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day16.model.Mirror;
import org.example.Solution.day16.model.MirrorGrid;
import org.example.Solution.model.grid.Grid;

public class Day16Solver extends AbstractDayXXSolver<Long> {
    private final Grid<Mirror> grid = new MirrorGrid(getDefaultPuzzleInputLines());

    @Override
    public Long partOneSolution() {
        grid.print();
        return null;
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
