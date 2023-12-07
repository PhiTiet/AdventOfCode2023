package org.example.Solution.day07;

import org.example.Solution.AbstractDayXSolver;
import org.example.Solution.day07.model.Hand;
import org.example.Solution.day07.model.comparator.PartTwoHandComparator;
import org.example.Solution.utils.AdventPart;
import org.example.Solution.utils.FileReader;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.reverseOrder;
import static org.example.Solution.utils.AdventPart.PART_ONE;
import static org.example.Solution.utils.AdventPart.PART_TWO;

public class Day07Solver extends AbstractDayXSolver {
    private final FileReader fileReader = new FileReader();
    private static final String PATH = "/day07.txt";

    @Override
    public Long partOneSolution() {
        List<String> lines = fileReader.getLines(PATH);
        List<Hand> hands = getHands(lines, PART_ONE);
        List<Hand> sorted = hands
                .stream().sorted(reverseOrder())
                .toList();
        return getWinnings(sorted);
    }

    @Override
    public Long partTwoSolution() {
        List<String> lines = fileReader.getLines(PATH);
        List<Hand> hands = getHands(lines, PART_TWO);
        var sorted = hands.stream()
                .sorted(reverseOrder(new PartTwoHandComparator()))
                .toList();
        return getWinnings(sorted);
    }

    private List<Hand> getHands(List<String> lines, AdventPart part) {
        return lines.stream().map(l -> fromLine(l, part)).toList();
    }

    private Hand fromLine(String line, AdventPart part) {
        var split = line.split(" ");
        return Hand.handPart(split[0], split[1], part);
    }

    private static long getWinnings(List<Hand> sorted) {
        return IntStream
                .range(0, sorted.size())
                .mapToLong(i -> (i + 1) * sorted.get(i).getBet())
                .sum();
    }
}
