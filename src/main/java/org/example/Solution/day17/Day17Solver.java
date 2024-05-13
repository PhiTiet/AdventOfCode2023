package org.example.Solution.day17;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day17.model.HeatGrid;

public class Day17Solver extends AbstractDayXXSolver<Long> {
    private HeatGrid heatGrid = new HeatGrid(getDefaultPuzzleInputLines());

    @Override
    public Long partOneSolution() {
        heatGrid.print();
        //Idea: before a new path is generated, check if it has already been passed.
        // If a path already was there before, do not branch left and right, only forward once
        return null;
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
