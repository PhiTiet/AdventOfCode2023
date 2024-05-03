package org.example.Solution.day12;

import org.apache.commons.lang3.SerializationUtils;
import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day12.model.ConditionRecord;
import org.example.Solution.day12.model.DamageState;
import org.example.Solution.utils.ArrayListUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
        ArrayList<List<Integer>> allPossibleSequences = getAllPossibleSequences(new ArrayList<>(record.getStates()), new ArrayList<>());
        return allPossibleSequences.stream().filter(a -> a.equals(record.getSequences())).count();
    }

    private ArrayList<List<Integer>> getAllPossibleSequences(ArrayList<DamageState> states, List<List<Integer>> allStates) {
        if (canEvaluateState(states)){
            return ArrayListUtils.ArrayListOf(sequenceFromState(states));
        }
        var operational = getAllPossibleSequences(statesWithNextUnknownChanged(states, DamageState.OPERATIONAL), allStates);
        var damaged = getAllPossibleSequences(statesWithNextUnknownChanged(states, DamageState.DAMAGED), allStates);
        operational.addAll(damaged);
        return operational;
    }

    public ArrayList<Integer> sequenceFromState(List<DamageState> states) {
        ArrayList<Integer> damagedSequences = new ArrayList<>();
        int count;
        for (int i = 0; i < states.size(); i++) {
            if (!(states.get(i) == DamageState.DAMAGED)){
                continue;
            }
            count = 1;
            while (i + count < states.size() && states.get(i + count) == DamageState.DAMAGED) {
                count++;
            }
            damagedSequences.add(count);
            i += count - 1;
        }
        return damagedSequences;
    }

    public ArrayList<DamageState> statesWithNextUnknownChanged(ArrayList<DamageState> states , DamageState newDamageState){
        ArrayList<DamageState> copyOfStates = SerializationUtils.clone(states);
        copyOfStates.set(copyOfStates.indexOf(DamageState.UNKNOWN), newDamageState);
        return copyOfStates;
    }
    public boolean canEvaluateState(List<DamageState> damageStates) {
        return !damageStates.contains(DamageState.UNKNOWN);
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
