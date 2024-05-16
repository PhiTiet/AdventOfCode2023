package org.example.Solution.day17;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day17.model.HeatGrid;
import org.example.Solution.day17.model.HeatTile;
import org.example.Solution.day17.model.Path;
import org.example.Solution.model.grid.Position;
import org.example.Solution.utils.ArrayListUtils;
import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.example.Solution.utils.model.Direction.*;

public class Day17Solver extends AbstractDayXXSolver<Long> {
    private final HeatGrid grid = new HeatGrid(getDefaultPuzzleInputLines());
    private final Position targetPosition = new Position(grid.getGridSize() - 1, grid.getGridSize() - 1);

    @Override
    public Long partOneSolution() {
        return findShortedPath(ArrayListUtils.ArrayListOf(new Path(EAST, new Position())));
    }

    private Long findShortedPath(ArrayList<Path> paths) {
        optimizeGridWithDepthFirstSearches();
        var newPaths = new ArrayList<Path>();
        var results = new ArrayList<Path>();
        while (!paths.isEmpty()) {
            System.out.println(paths.size());
            System.out.println(paths.get(0).getTotalHeat());
            for (var path : paths) {

                if ((path.getTotalHeat() > 1012)) {
                    continue;
                }
                if (!results.isEmpty() && results.stream().anyMatch(a -> a.getTotalHeat() < path.getTotalHeat())) {
                    continue;
                }

                HeatTile currentTile = grid.getElementAt(path.getPosition());
                if (path.getPosition().equals(targetPosition)) {
                    path.getPrevious().add(new Position(path.getPosition()));
                    path.addHeat(currentTile.getHeat());
                    results.add(path);
                    continue;
                }

                if (currentTile.getPassedRecords().shouldSkip(path)) {
                    continue;
                }
                Path left = new Path(path).withStepsTaken(0L).withDirection(getTurnLeft(path.getDirection()));
                Path right = new Path(path).withStepsTaken(0L).withDirection(getTurnRight(path.getDirection()));

                travel(path, currentTile, newPaths);
                travel(left, currentTile, newPaths);
                travel(right, currentTile, newPaths);

            }
            paths = newPaths;
            newPaths = new ArrayList<>();
        }
        var sortedHeatsAndPaths = new TreeMap<>(results.stream().collect(Collectors.groupingBy(Path::getTotalHeat)));
        List<ArrayList<Position>> fastestRoutes = sortedHeatsAndPaths.firstEntry().getValue().stream().map(Path::getPrevious).toList();

        for (var fastestRoute : fastestRoutes) {
            grid.printElements(fastestRoute, "#");
        }
        return results.stream()
                .map(Path::getTotalHeat)
                .min(Long::compareTo).orElseThrow() - grid.getElementAt(0L, 0L).getHeat();
    }

    private void optimizeGridWithDepthFirstSearches() {
        var results = new ArrayList<Path>();
        var pathToGoal = generatePaths();
        for (int i = 0; i < 1000 ; i++) {
            var path = new Path(EAST, new Position());
            for (Direction direction : pathToGoal) {
                path = path.withDirection(direction).withStepsTaken(0L);
                var currentTile = grid.getElementAt(path.getPosition());
                currentTile.getPassedRecords().addRecord(path);
                path.addHeat(currentTile.getHeat());
                if (path.willBeOutOfRange(grid.getGridSize())) {
                    continue;
                }
                path.travel();
            }
            results.add(path);
        }

//        var paths = results.stream().map(Path::getPrevious).toList();
//        grid.printElements(paths.getFirst(), "#");
        System.out.println("Grid initialized with 1000 depth first paths");
    }

    private ArrayList<Direction> generatePaths() {
        var pathToGoal = new ArrayList<Direction>();
        int south = grid.getGridSize();
        int east = grid.getGridSize();
        while (south > 0 || east > 0) {
            var actualSouth = Math.min(south, new Random().nextInt(3) + 1);
            for (int j = 0; j < actualSouth; j++) {
                pathToGoal.add(SOUTH);
            }
            south -= actualSouth;
            var actualEast = Math.min(east, new Random().nextInt(3) + 1);
            for (int j = 0; j < actualEast; j++) {
                pathToGoal.add(EAST);
            }
            east -= actualEast;
        }
        return pathToGoal;
    }

    private void travel(Path path, HeatTile currentTile, ArrayList<Path> newPaths) {
        if (!path.canMoveForward() || path.willBeOutOfRange(grid.getGridSize())) {
            return;
        }
        currentTile.getPassedRecords().addRecord(path);
        path.addHeat(currentTile.getHeat());
        path.travel();
        newPaths.add(path);
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
