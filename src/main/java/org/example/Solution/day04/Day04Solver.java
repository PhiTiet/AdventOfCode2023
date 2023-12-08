package org.example.Solution.day04;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day04.model.ScratchCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Day04Solver extends AbstractDayXXSolver {

    @Override
    public Long partOneSolution() {
        List<ScratchCard> cards = getScratchCards();
        return cards.stream().map(ScratchCard::getScore).reduce(0L, Long::sum);
    }

    @Override
    public Long partTwoSolution() {
        List<Long> intersections = getScratchCards().stream()
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

    private List<ScratchCard> getScratchCards() {
        return getDefaultPuzzleInputLines()
                .stream()
                .map(a -> a.split(": ")[1])
                .map(ScratchCard::new)
                .toList();
    }
}
