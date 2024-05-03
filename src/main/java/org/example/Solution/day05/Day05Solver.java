package org.example.Solution.day05;

import org.apache.commons.lang3.Range;
import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day05.model.MapperChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.Solution.utils.RegexUtils.SYSTEM_NEWLINE;

public class Day05Solver extends AbstractDayXXSolver<Long> {
    private final List<String> lines = getDefaultPuzzleInputWithDelimiter(SYSTEM_NEWLINE + SYSTEM_NEWLINE);
    private final MapperChain mapperChain;

    public Day05Solver() {
        this.mapperChain = new MapperChain(lines.subList(1, lines.size()));
    }

    @Override
    public Long partOneSolution() {
        var inputs = getInput();
        return inputs.stream()
                .map(mapperChain::map)
                .min(Long::compare)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public Long partTwoSolution() {
        var inputs = getRangeInput();
        var mapped = mapperChain.map(inputs);
        return mapped.stream().map(Range::getMinimum).sorted().findFirst().get();
    }

    private ArrayList<Range<Long>> getRangeInput() {
        var longs = getInput();
        var ret = new ArrayList<Range<Long>>();
        for (int i = 0; i < longs.size(); i += 2) {
            ret.add(Range.of(longs.get(i), longs.get(i) + longs.get(i + 1)));
        }
        return ret;
    }

    private List<Long> getInput() {
        return Arrays.stream(lines.get(0).split(" ")).map(Long::parseLong).toList();
    }


}
