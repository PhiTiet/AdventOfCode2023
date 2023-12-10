package org.example.Solution.day09;

import org.example.Solution.AbstractDayXXSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day09Solver extends AbstractDayXXSolver<Long> {
    private final List<String> rawLines = getDefaultPuzzleInputLines();

    @Override
    public Long partOneSolution() {
        var sequences = getSequences(rawLines);
        return sequences.stream().map(this::getLastDifference).reduce(0L, Long::sum);
    }

    @Override
    public Long partTwoSolution() {
        var sequences = getSequences(rawLines).stream().map(List::reversed).toList();
        return sequences.stream().map(this::getLastDifference).reduce(0L, Long::sum);
    }

    private List<List<Long>> getSequences(List<String> rawLines) {
        return rawLines.stream()
                .map(a -> Arrays.stream(a.split(" ")).map(Long::parseLong).toList())
                .toList();
    }

    private Long getLastDifference(List<Long> sequence) {
        if (sumIsZero(sequence)) {
            return 0L;
        }
        var differences = getDifferences(sequence);
        return sequence.getLast() + getLastDifference(differences);
    }

    private List<Long> getDifferences(List<Long> sequence) {
        var ret = new ArrayList<Long>();
        for (int i = 1; i < sequence.size(); i++) {
            ret.add(sequence.get(i) - sequence.get(i - 1));
        }
        return ret;
    }

    private boolean sumIsZero(List<Long> sequence) {
        return sequence.stream().allMatch(l -> l.equals(0L));
    }
}
