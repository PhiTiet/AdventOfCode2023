package org.example.Solution.model;

import lombok.Getter;

@Getter
public class GameSample {
    private int blue;
    private int green;
    private int red;

    public GameSample(String sampleLine) {
        var draws = sampleLine.split(", ");

        for (var draw : draws){
            var temp = draw.split(" ");

            int amount = Integer.parseInt(temp[0]);
            String color = temp[1];

            if (color.equals("blue")){
                blue = amount;
            }
            if (color.equals("green")){
                green = amount;
            }
            if (color.equals("red")){
                red = amount;
            }
        }
    }
    public boolean isPossible(CubeUpperLimit limit){
        return limit.blue() >= blue && limit.green() >= green && limit.red() >= red;
    }
}
