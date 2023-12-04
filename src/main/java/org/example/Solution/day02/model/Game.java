package org.example.Solution.day02.model;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
public class Game {
    public static final String DELIMITER = "; ";
    private final int gameId;
    private final List<GameSample> samples;

    public Game(int gameId, String line) {
        this.gameId = gameId;
        samples = Stream.of(line.split(DELIMITER)).map(GameSample::new).toList();
    }

    public CubeUpperLimit getCubeUpperLimit() {
        var maxBlue = 0;
        var maxGreen = 0;
        var maxRed = 0;

        for (var sample : samples) {
            if (sample.getBlue() > maxBlue) {
                maxBlue = sample.getBlue();
            }
            if (sample.getGreen() > maxGreen) {
                maxGreen = sample.getGreen();
            }
            if (sample.getRed() > maxRed) {
                maxRed = sample.getRed();
            }
        }
        return new CubeUpperLimit(maxBlue, maxGreen, maxRed);
    }

    public boolean possible(CubeUpperLimit limit) {
        return samples.stream().allMatch(a -> a.isPossible(limit));
    }
}
