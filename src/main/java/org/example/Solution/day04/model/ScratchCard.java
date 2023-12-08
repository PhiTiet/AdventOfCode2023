package org.example.Solution.day04.model;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;
import static org.example.Solution.utils.RegexUtils.WHITE_SPACES;

public class ScratchCard {
    private static final String CARD_SPLIT = "\\|";

    private final List<Long> numbers;
    private final List<Long> winningNumbers;

    public ScratchCard(String line) {
        var split = bisectLineAndTrim(line);
        numbers = toListOfLongs(split.get(0));
        winningNumbers = toListOfLongs(split.get(1));
    }

    public long getIntersection() {
        return numbers.stream().filter(winningNumbers::contains).count();
    }

    public long getScore() {
        var intersections = getIntersection();
        return intersections == 0 ? 0 : (long) pow(2, intersections - 1);
    }

    private List<String> bisectLineAndTrim(String line) {
        return Arrays.stream(line.split(CARD_SPLIT)).map(String::trim).toList();
    }

    private List<Long> toListOfLongs(String line) {
        return Arrays.stream(line.split(WHITE_SPACES)).map(Long::parseLong).toList();
    }

}
