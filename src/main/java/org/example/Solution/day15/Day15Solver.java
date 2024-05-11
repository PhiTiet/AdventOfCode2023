package org.example.Solution.day15;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day15.model.ElfHasher;

import java.util.Arrays;
import java.util.List;

public class Day15Solver extends AbstractDayXXSolver<Integer> {
    private final List<String> unhashed = Arrays.stream(getDefaultPuzzleInputLines().get(0).split(",")).toList();
    private final ElfHasher elfHasher = new ElfHasher();
    @Override
    public Integer partOneSolution() {
        return unhashed.stream()
                .map(elfHasher::hashString)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer partTwoSolution() {
        return null;
    }
}
