package org.example.Solution.day04;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day04.model.ScratchCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Day04Solver extends AbstractDayXXSolver<Long> {
    private final List<ScratchCard> cards;

    public Day04Solver() {
        this.cards = getDefaultPuzzleInputLines()
                .stream()
                .map(a -> a.split(": ")[1])
                .map(ScratchCard::new)
                .toList();
    }

    @Override
    public Long partOneSolution() {
        return cards.stream().map(ScratchCard::getScore).reduce(0L, Long::sum);
    }

    @Override
    public Long partTwoSolution() {
        List<Long> intersections = cards.stream()
                .map(ScratchCard::getIntersection)
                .toList();
        return calculateCopies(intersections);
    }

    private Long calculateCopies(List<Long> intersections) {
        List<Long> copies = new ArrayList<>(Collections.nCopies(intersections.size(), 1L));
        for (int i = 0; i < intersections.size(); i++) {
            for (int j = 1; j <= intersections.get(i); j++) {
                copies.set(i + j, copies.get(i) + copies.get(i + j));
            }
        }
        return copies.stream().reduce(0L, Long::sum);
    }

}
