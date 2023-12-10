package org.example.Solution.day10;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day10.model.Direction;
import org.example.Solution.day10.model.GroundTile;
import org.example.Solution.utils.grid.Grid;
import org.example.Solution.utils.grid.GridElement;

import java.util.List;

import static org.example.Solution.day10.model.GroundType.exitDirection;

public class Day10Solver extends AbstractDayXXSolver {
    private static final long START_X = 88;
    private static final long START_Y = 69;
    private static final Direction START_DIRECTION = Direction.EAST;
    private final List<String> rawLines = getDefaultPuzzleInputLines();

    @Override
    public Long partOneSolution() {
        Grid<GroundTile> grid = new Grid<>(rawLines, GroundTile.class);
        var start = grid.getElementAt(START_X, START_Y);

        return traverseGrid(start, grid);
    }

    private long traverseGrid(GroundTile start, Grid<GroundTile> grid) {
        GroundTile current = start;
        Direction direction = START_DIRECTION;
        long iterations = 0;

        do {
            current = traverseAndGet(grid, current, direction);
            direction = exitDirection(Direction.getReverse(direction), current.getGroundType());
            iterations++;
        } while (!current.equals(start));

        return iterations / 2;
    }

    private GroundTile traverseAndGet(Grid<GroundTile> grid, GridElement current, Direction direction) {
        switch (direction) {
            case NORTH -> {
                return grid.getElementAt(current.getX(), current.getY() - 1);
            }
            case EAST -> {
                return grid.getElementAt(current.getX() + 1, current.getY());
            }
            case SOUTH -> {
                return grid.getElementAt(current.getX(), current.getY() + 1);
            }
            case WEST -> {
                return grid.getElementAt(current.getX() - 1, current.getY());
            }
        }
        throw new IllegalArgumentException("rip");
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
