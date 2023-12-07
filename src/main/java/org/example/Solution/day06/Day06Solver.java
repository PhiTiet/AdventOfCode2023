package org.example.Solution.day06;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day06.model.RaceRecord;
import org.example.Solution.day06.model.WinningStrategiesFinder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.min;
import static org.example.Solution.utils.RegexUtils.WHITE_SPACES;

public class Day06Solver extends AbstractDayXXSolver {
    private final WinningStrategiesFinder winningStrategiesFinder = new WinningStrategiesFinder();
    private static final String PATH_PART_TWO = "/day06p2.txt";

    @Override
    public Long partOneSolution() {
        return getSolutionWithPart(getPuzzleInputPath());
    }

    @Override
    public Long partTwoSolution() {
        return getSolutionWithPart(PATH_PART_TWO);
    }

    private Long getSolutionWithPart(String pathPartOne) {
        var lines = fileReader.getLines(pathPartOne);
        List<RaceRecord> records = getRaceRecords(lines);
        return records.stream()
                .map(winningStrategiesFinder::getAllWinningStrategies)
                .map(List::size)
                .map(Integer::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    private List<RaceRecord> getRaceRecords(List<String> lines) {
        var timesRaw = lines.get(1).split(WHITE_SPACES);
        var distanceRaw = lines.get(0).split(WHITE_SPACES);
        var times = toListOfLong(timesRaw);
        var distance = toListOfLong(distanceRaw);

        return IntStream.range(0, min(times.size(), distance.size()))
                .mapToObj(i -> new RaceRecord(times.get(i), distance.get(i)))
                .toList();
    }

    private static List<Long> toListOfLong(String[] distanceRaw) {
        return Arrays.stream(distanceRaw)
                .filter(NumberUtils::isCreatable)
                .map(Long::parseLong)
                .toList();

    }
}
