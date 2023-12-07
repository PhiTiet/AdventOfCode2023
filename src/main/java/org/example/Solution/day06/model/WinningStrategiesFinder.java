package org.example.Solution.day06.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class WinningStrategiesFinder {
    private final BiFunction<Long, Long, Long> distanceTraveled = (startup, time) -> startup * (time - startup);

    public List<RaceStrategy> getAllWinningStrategies(RaceRecord raceRecord){
        List<RaceStrategy> winningStrategies = new ArrayList<>();
        for (long i = 0; i < raceRecord.timeLimit(); i++){
            if (distanceTraveled.apply(i, raceRecord.timeLimit()) > raceRecord.distanceTraveled()){
                winningStrategies.add(new RaceStrategy(i));
            }
        }
        return winningStrategies;
    }
}
