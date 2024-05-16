package org.example.Solution.day17.model;

import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassedRecords {
    private Map<Direction, Boolean> hasPassedBy = new HashMap<>();
    private Map<Direction, ArrayList<PassedRecord>> passedByRecords = new HashMap<>();

    public PassedRecords() {
        var values = List.of(Direction.values());
        values.forEach(d -> hasPassedBy.put(d, false));
        values.forEach(d -> passedByRecords.put(d, new ArrayList<>()));
    }

    public boolean shouldSkip(Path path){
        if (!hasPassedBy.get(path.getDirection())){
            return false;
        }
        var records = passedByRecords.get(path.getDirection());
        return records.stream().anyMatch(a -> a.totalHeat() < path.getTotalHeat() && a.stepsTaken() <= path.getStepsTaken());
    }

    public void addRecord(Path path) {
        hasPassedBy.replace(path.getDirection(), true);
        passedByRecords.get(path.getDirection()).add(
                PassedRecord.builder()
                        .totalHeat(path.getTotalHeat())
                        .stepsTaken(path.getStepsTaken())
                .build());

    }
}
