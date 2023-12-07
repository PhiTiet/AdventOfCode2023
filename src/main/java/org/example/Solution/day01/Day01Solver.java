package org.example.Solution.day01;

import org.example.Solution.DayXSolver;
import org.example.Solution.utils.FileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections.MapUtils.invertMap;

public class Day01Solver implements DayXSolver {
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
    private final FileReader fileReader = new FileReader();
    private static final String PATH = "/day01.txt";
    @Override
    public Long partOneSolution() {
        var lines = fileReader.getLines(PATH);
        return lines.stream()
                .map(this::removeChars)
                .map(this::removeMiddleChars)
                .map(Long::parseLong)
                .reduce(Long::sum)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Long partTwoSolution() {
        var lines = fileReader.getLines(PATH);
        return lines.stream()
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


    private String removeMiddleChars(String numbers) {
        if (numbers.length() < 2){
            return numbers + numbers;
        }
        return numbers.substring(0, 1) + numbers.substring(numbers.length() - 1);
    }

    private String removeChars(String s) {
        return s.replaceAll("\\D", "");
    }

}
