package org.example.Solution.day14;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day14.model.RockGrid;

public class Day14Solver extends AbstractDayXXSolver<Long> {
    private RockGrid rockGrid = new RockGrid(getDefaultPuzzleInputLines());

    @Override
    public Long partOneSolution() {
        rockGrid.slideNorth();
        return rockGrid.getScore();
    }

    @Override
    public Long partTwoSolution() {
        rockGrid = new RockGrid(getDefaultPuzzleInputLines());
        //Assuming the board gets stuck in a cycle 10000000 can be simplified to 1000 or even lower idk
        for (int i = 0; i < 1000; i++) {
            cycle();
        }

        return rockGrid.getScore();
    }

    private void cycle(){
        for (int i = 0; i < 4; i++) {
            rockGrid.slideNorth();
            rockGrid.rotate90CounterClockwise();
        }
    }
}
