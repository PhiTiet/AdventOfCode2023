package org.example.Solution.day16.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Solution.utils.model.Direction;

@AllArgsConstructor
@Getter
public class LightBeam {
    private Direction direction;
    private long x;
    private long y;

    public void travel(){
        switch(direction){
            case NORTH -> {
                y--;
            }
            case EAST -> {
                x++;
            }
            case SOUTH -> {
                y++;
            }
            case WEST -> {
                x--;
            }
        }
    }
}
