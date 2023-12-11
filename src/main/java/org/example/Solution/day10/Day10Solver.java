package org.example.Solution.day10;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day10.model.Direction;
import org.example.Solution.day10.model.GroundTile;
import org.example.Solution.model.grid.Grid;
import org.example.Solution.model.grid.GridElement;

import java.util.ArrayList;
import java.util.List;

import static org.example.Solution.day10.model.GroundType.GROUND;
import static org.example.Solution.day10.model.GroundType.exitDirection;

public class Day10Solver extends AbstractDayXXSolver<Long> {
    private static final long START_X = 88;
    private static final long START_Y = 69;
    private static final Direction START_DIRECTION = Direction.EAST;
    private final List<String> rawLines = getDefaultPuzzleInputLines();

    @Override
    public Long partOneSolution() {
        Grid<GroundTile> grid = new Grid<>(rawLines, GroundTile.class);
        var start = grid.getElementAt(START_X, START_Y);

        return getFurthestSquareDistance(start, grid);
    }

    @Override
    public Long partTwoSolution() {
        Grid<GroundTile> grid = new Grid<>(rawLines, GroundTile.class);
        var traversedSquares = getTraversedSquares(grid.getElementAt(START_X, START_Y), grid);
        grid.filterGrid(traversedSquares);
        grid.map(a -> new GroundTile(a.getX(), a.getY(), a.getSymbol()));
        long insides = 0;
        for (var element : grid.getElements()) {
            if (element.getGroundType() != GROUND) {
                continue;
            }
            var numVerticalPipes = 0;
            var numNorthernCorners = 0;
            var southernCorners = 0;
            long counter = 1;
            while (grid.getElementAt(element.getX() + counter, element.getY()) != null) {
                var next = grid.getElementAt(element.getX() + counter, element.getY());
                switch (next.getGroundType()) {
                    case VERTICAL_PIPE -> {
                        numVerticalPipes++;
                    }
                    case NORTH_EAST_PIPE, NORTH_WEST_PIPE -> {
                        numNorthernCorners++;
                    }
                    case SOUTH_EAST_PIPE, SOUTH_WEST_PIPE -> {
                        southernCorners++;
                    }
                }
                counter++;
            }
            insides += ((long) Math.min(southernCorners, numNorthernCorners) + numVerticalPipes) % 2;
        }
        return insides;
    }

    private long getFurthestSquareDistance(GroundTile start, Grid<GroundTile> grid) {
        return getTraversedSquares(start, grid).size() / 2;
    }

    private List<GroundTile> getTraversedSquares(GroundTile start, Grid<GroundTile> grid) {
        GroundTile current = start;
        Direction direction = START_DIRECTION;

        var visited = new ArrayList<GroundTile>();

        do {
            current = traverseAndGet(grid, current, direction);
            visited.add(current);
            direction = exitDirection(Direction.getReverse(direction), current.getGroundType());

        } while (!current.equals(start));

        return visited;
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


}
