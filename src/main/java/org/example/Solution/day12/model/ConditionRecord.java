package org.example.Solution.day12.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ConditionRecord {
    private List<DamageState> states;
    private List<Integer> sequences;

    public boolean cantEvaluateState() {
        return states.contains(DamageState.UNKNOWN);
    }

    public List<Integer> sequenceFromState() {
        if (cantEvaluateState()) {
            throw new IllegalStateException("cannot generate sequence with unknown states");
        }
        List<Integer> damagedSequences = new ArrayList<>();
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
}
