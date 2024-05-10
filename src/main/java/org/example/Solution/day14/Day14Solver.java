package org.example.Solution.day14;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day14.model.RockGrid;

import java.util.ArrayList;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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
        for (int i = 0; i < 1000; i++) {
            cycle();
        }
        System.out.println("if this one fails please check code and check line 27");
//        cycleDetection();

        return rockGrid.getScore();
    }

    private void cycleDetection() {
        var list = new ArrayList<Long>();
        for (int i = 0; i < 100; i++) {
            cycle();
            list.add(rockGrid.getScore());
        }
        //put a breakpoint here to see nodes it goes through when it eventually reaches a cycle.
        var countMap = list.stream().collect(groupingBy(c -> c, counting()));
    }

    private void cycle(){
        for (int i = 0; i < 4; i++) {
            rockGrid.slideNorth();
            rockGrid.rotate90CounterClockwise();
        }
    }
}
