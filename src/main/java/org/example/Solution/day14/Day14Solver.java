package org.example.Solution.day14;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day14.model.RockGrid;

public class Day14Solver extends AbstractDayXXSolver<Long> {
    private final RockGrid rockGrid = new RockGrid(getDefaultPuzzleInputLines());

    @Override
    public Long partOneSolution() {
        rockGrid.print();
        return null;
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
