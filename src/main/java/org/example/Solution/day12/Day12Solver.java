package org.example.Solution.day12;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day12.model.ConditionRecord;
import org.example.Solution.day12.model.DamageState;

import java.util.Arrays;
import java.util.List;

public class Day12Solver extends AbstractDayXXSolver<Long> {

    private final List<ConditionRecord> records = getConditionRecords();

    @Override
    public Long partOneSolution() {
        return records.stream()
                .map(this::getNumberOfLegalSequences)
                .reduce(0L, Long::sum);
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
    private Long getNumberOfLegalSequences(ConditionRecord record){
        return 0L;
    }

    private List<ConditionRecord> getConditionRecords() {
        return getDefaultPuzzleInputLines().stream().map(this::recordFromString).toList();
    }

    private ConditionRecord recordFromString(String line){
        var conditionsString = line.split(" ")[0].split("");
        var sequencesString = line.split(" ")[1];

        List<DamageState> conditions = Arrays.stream(conditionsString).map(DamageState::fromString).toList();
        List<Integer> sequences = Arrays.stream(sequencesString.split(",")).map(Integer::valueOf).toList();
        return new ConditionRecord(conditions, sequences);
    }
}
