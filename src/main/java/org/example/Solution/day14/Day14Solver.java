package org.example.Solution.day14;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day14.model.RockGrid;

public class Day14Solver extends AbstractDayXXSolver<Long> {
    private final RockGrid rockGrid = new RockGrid(getDefaultPuzzleInputLines());

    @Override
    public Long partOneSolution() {
        rockGrid.slideNorth();
//        return rockGrid.getScore();
        return null;
    }

    @Override
    public Long partTwoSolution() {
        rockGrid.print();
        System.out.println("------");
        rockGrid.rotate90Clockwise();
        rockGrid.print();
        return null;
    }
}
