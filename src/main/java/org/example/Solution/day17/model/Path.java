package org.example.Solution.day17.model;

import lombok.*;
import org.example.Solution.model.grid.Position;
import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@With
public class Path {
    private Position position;
    private Direction direction;
    long totalHeat;
    private ArrayList<Position> previous = new ArrayList<>();
    private long stepsTaken = 0L;

    public Path(Direction direction, Position position) {
        this.direction = direction;
        this.position = position;
    }
    public Path(Path path){
        position = new Position(path.getPosition());
        direction = path.getDirection();
        totalHeat = path.getTotalHeat();
        stepsTaken = path.getStepsTaken();
        previous.addAll(path.previous);
    }

    public void travel() {
        switch (direction) {
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
        stepsTaken++;
    }

    public long getX() {
        return position.getX();
    }

    public long getY() {
        return position.getY();
    }

    public boolean canMoveForward() {
        return stepsTaken < 3;
    }

    public boolean willBeOutOfRange(long gridSize){
        return switch (direction){
            case NORTH -> position.getY() - 1 < 0;
            case EAST -> position.getX() + 1 > gridSize - 1;
            case SOUTH -> position.getY() + 1 > gridSize - 1;
            case WEST -> position.getX() - 1 < 0;
        };
    }
    public void addToTotal(long heat){
        totalHeat += heat;
    }
}
