package org.example.Solution.day04.model;

import java.util.Arrays;
import java.util.List;

public class ScratchCard {
    private static final String WHITESPACE = "\\s+";
    private static final String CARD_SPLIT = "\\|";

    private final List<Integer> numbers;
    private final List<Integer> winningNumbers;

    public ScratchCard(String line){
        var split = bisectLineAndTrim(line);
        numbers = toIntegerList(split.get(0));
        winningNumbers = toIntegerList(split.get(1));
    }

    public int getIntersection(){
        return numbers.stream().filter(winningNumbers::contains).toList().size();
    }

    public int getScore(){
        var intersections = getIntersection();
        return intersections == 0 ? 0 : (int) Math.pow(2, intersections - 1);
    }

    private List<String> bisectLineAndTrim(String line) {
        return Arrays.stream(line.split(CARD_SPLIT)).map(String::trim).toList();
    }

    private List<Integer> toIntegerList(String line) {
        return Arrays.stream(line.split(WHITESPACE)).map(Integer::parseInt).toList();
    }

}
