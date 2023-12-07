package org.example.Solution.day02.model;

import lombok.Getter;

@Getter
public class GameSample {
    private long blue;
    private long green;
    private long red;

    public GameSample(String sampleLine) {
        var draws = sampleLine.split(", ");

        for (var draw : draws) {
            var temp = draw.split(" ");

            long amount =Long.parseLong(temp[0]);
            String color = temp[1];

            if (color.equals("blue")) {
                blue = amount;
            }
            if (color.equals("green")) {
                green = amount;
            }
            if (color.equals("red")) {
                red = amount;
            }
        }
    }

    public boolean isPossible(CubeUpperLimit limit) {
        return limit.blue() >= blue && limit.green() >= green && limit.red() >= red;
    }
}
