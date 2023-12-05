package org.example.Solution.day05;

import org.apache.commons.lang3.Range;
import org.example.Solution.DayXSolver;
import org.example.Solution.day05.model.MapperChain;
import org.example.Solution.utils.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05Solver implements DayXSolver {

    private final FileReader fileReader = new FileReader();
    private static final String PATH = "/day05.txt";
    private static final String DELIMITER = "\r\n\r\n";
    @Override
    public Long partOneSolution() {
        var inputs = getInput();
        var chain = getChain();
        return inputs.stream().map(chain::map).min(Long::compare).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Long partTwoSolution() {
        var inputs = getRangeInput();
        var chain = getChain();
        var mapped = chain.map(inputs);
        return mapped.stream().map(Range::getMinimum).sorted().findFirst().get();
    }

    private ArrayList<Range<Long>> getRangeInput() {
        var longs = getInput();
        var ret = new ArrayList<Range<Long>>();
        for (int i = 0; i < longs.size(); i += 2){
            ret.add(Range.of(longs.get(i), longs.get(i) + longs.get(i + 1)));
        }
        return ret;
    }

    private List<String> getLines() {
        return fileReader.getLines(PATH, DELIMITER);
    }

    private MapperChain getChain() {
        var lines = getLines();
        return new MapperChain(lines.subList(1, lines.size()));
    }

    private List<Long> getInput() {
        return Arrays.stream(getLines().get(0).split(" ")).map(Long::parseLong).toList();
    }


}
