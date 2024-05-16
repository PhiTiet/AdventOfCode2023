package org.example.Solution.day17.model;

import org.example.Solution.utils.model.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassedRecords {
    private Map<Direction, Map<Long, Long>> passedByRecords = new HashMap<>();

    public PassedRecords() {
        var values = List.of(Direction.values());
        values.forEach(d -> passedByRecords.put(d, new HashMap<>()));
    }

    public boolean shouldSkip(Path path){
        if (passedByRecords.get(path.getDirection()).isEmpty()){
            return false;
        }
        var records = passedByRecords.get(path.getDirection());
        return records.entrySet().stream().anyMatch(a -> a.getKey() <= path.getStepsTaken() && a.getValue() < path.getTotalHeat());
    }

    public void addRecord(Path path) {
        var records = passedByRecords.get(path.getDirection());
        if (!records.containsKey(path.getStepsTaken())){
            records.put(path.getStepsTaken(), path.getTotalHeat());
        }
        if (records.get(path.getStepsTaken()) > path.getTotalHeat()){
            records.replace(path.getStepsTaken(), path.getTotalHeat());
        }
    }
}
