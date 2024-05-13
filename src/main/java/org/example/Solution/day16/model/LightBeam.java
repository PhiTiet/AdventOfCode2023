package org.example.Solution.day16.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.Solution.model.grid.Position;
import org.example.Solution.utils.model.Direction;

@Getter
@Setter
@AllArgsConstructor
public class LightBeam {
    private Direction direction;
    private Position position;

    public LightBeam(Direction direction) {
        this.direction = direction;
        position = new Position();
    }

    public void travel(){
        switch(direction){
            case NORTH -> {
                position.decrementY();
            }
            case EAST -> {
                position.incrementX();
            }
            case SOUTH -> {
                position.incrementY();
            }
            case WEST -> {
                position.decrementX();
            }
        }
    }
}
