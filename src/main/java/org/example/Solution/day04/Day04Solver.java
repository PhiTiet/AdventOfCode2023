package org.example.Solution.day04;

import org.example.Solution.DayXSolver;
import org.example.Solution.day04.model.ScratchCard;
import org.example.Solution.utils.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Day04Solver implements DayXSolver {
    private final FileReader fileReader = new FileReader();
    private static final String PATH = "/day04.txt";

    @Override
    public Integer partOneSolution() {
        List<ScratchCard> cards = getScratchCards();

        return cards.stream().map(ScratchCard::getScore).reduce(0, Integer::sum);
    }

    @Override
    public Integer partTwoSolution(){
        List<Integer> intersections = getScratchCards().stream()
                .map(ScratchCard::getIntersection)
                .toList();
        return calculateCopies(intersections);
    }

    private Integer calculateCopies(List<Integer> intersections) {

        List<Integer> copies = new ArrayList<>(Collections.nCopies(intersections.size(), 1));
        for (int i = 0; i < intersections.size(); i++){
            for (int j = 1; j <= intersections.get(i); j++){
                copies.set(i + j, copies.get(i) + copies.get(i + j));
            }
        }
        return copies.stream().reduce(0, Integer::sum);
    }

    private List<ScratchCard> getScratchCards() {
        return fileReader.getLines(PATH)
                .stream()
                .map(a -> a.split(": ")[1])
                .map(ScratchCard::new)
                .toList();
    }
}
