package org.example.Solution.day12;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day12.model.ConditionRecord;

import java.util.*;

public class Day12Solver extends AbstractDayXXSolver<Long> {

    private final List<ConditionRecord> records = getConditionRecords();
    private Map<String, Long> previouslySearched = new HashMap<>();

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

    private Long getNumberOfLegalSequences(ConditionRecord record) {
        Long allPossibleSequences = getAllPossibleSequences(record.getStates(), record.getSequences());
        return allPossibleSequences;
    }

    private Long getAllPossibleSequences(String damageState, String matchingStates) {
        if (previouslySearched.containsKey(damageState+matchingStates)) {
            return previouslySearched.get(damageState+matchingStates);
        }
        if (canEvaluateState(damageState)) {
            return sequenceFromState(damageState).equals(matchingStates) ? 1L : 0L;
        }

        long matching = getAllPossibleSequences(statesWithNextUnknownChanged(damageState, "."), matchingStates) +
                getAllPossibleSequences(statesWithNextUnknownChanged(damageState, "#"), matchingStates);
        previouslySearched.put(damageState+matchingStates, matching);
        return matching;
    }

    public String sequenceFromState(String states) {
        List<String> damagedSequences = new ArrayList<>();
        int count;
        for (int i = 0; i < states.length(); i++) {
            if (!(states.charAt(i) == '#')) {
                continue;
            }
            count = 1;
            while (i + count < states.length() && states.charAt(i + count) == '#') {
                count++;
            }
            damagedSequences.add(String.valueOf(count));
            i += count - 1;
        }
        return String.join(",", damagedSequences);
    }

    public String statesWithNextUnknownChanged(String damageState, String newValue) {
        String copyOfStates = new String(damageState);
        return copyOfStates.replaceFirst("\\?", newValue);

    }

    public boolean canEvaluateState(String damageStates) {
        return !damageStates.contains("?");
    }

    private List<ConditionRecord> getConditionRecords() {
        return getDefaultPuzzleInputLines().stream().map(this::recordFromString).toList();
    }

    private ConditionRecord recordFromString(String line) {
        var conditionsString = line.split(" ")[0];
        var sequencesString = line.split(" ")[1];


        return new ConditionRecord(conditionsString, sequencesString);
    }
}
