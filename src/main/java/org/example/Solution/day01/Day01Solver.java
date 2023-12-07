package org.example.Solution.day01;

import org.example.Solution.AbstractDayXXSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections.MapUtils.invertMap;

public class Day01Solver extends AbstractDayXXSolver {
    private final List<String> rawLines = getDefaultPuzzleInputLines();
    private static final Map<String, Integer> STRING_TO_INT_MAP = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);

    private static final Map<Integer,String> INT_TO_STRING_MAP = invertMap(STRING_TO_INT_MAP);

    @Override
    public Long partOneSolution() {
        return rawLines.stream()
                .map(this::removeChars)
                .map(this::retainOuterNumericalCharacters)
                .map(Long::parseLong)
                .reduce(Long::sum)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Long partTwoSolution() {
        return rawLines.stream()
                .map(this::writeOutNumbers)
                .map(this::convertToListOfNumbers)
                .map(this::getDesiredNumber)
                .reduce(Long::sum)
                .orElseThrow(RuntimeException::new);
    }

    private Long getDesiredNumber(List<Integer> integers) {
        return Long.parseLong(integers.get(0).toString() + integers.get(integers.size() -1).toString());
    }

    private List<Integer> convertToListOfNumbers(String numberString) {
        Map<Integer, Integer> numbersByIndex = new HashMap<>();
        for (var entry : STRING_TO_INT_MAP.entrySet()){
            var index = numberString.indexOf(entry.getKey());
            while (index >= 0){
                numbersByIndex.put(index, entry.getValue());
                index = numberString.indexOf(entry.getKey(), index + 1);
            }
        }
        return numbersByIndex.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();
    }

    private String writeOutNumbers(String line) {
        for (var entry : INT_TO_STRING_MAP.entrySet()){
            line = line.replaceAll(entry.getKey().toString(), entry.getValue());
        }
        return line;

    }

    private String retainOuterNumericalCharacters(String numbers) {
        if (numbers.isEmpty()){
            return "0";
        }
        if (numbers.length() == 1){
            return numbers + numbers;
        }
        return numbers.substring(0, 1) + numbers.substring(numbers.length() - 1);
    }

    private String removeChars(String s) {
        return s.replaceAll("\\D", "");
    }

}
