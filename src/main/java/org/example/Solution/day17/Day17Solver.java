package org.example.Solution.day17;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day17.model.HeatGrid;
import org.example.Solution.day17.model.HeatTile;
import org.example.Solution.day17.model.Path;
import org.example.Solution.model.grid.Position;
import org.example.Solution.utils.ArrayListUtils;
import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        long totalSteps = 0L;
        long prune1, prune2, prune3, prune4;

        while (!paths.isEmpty()) {
            prune1 = 0; prune2 = 0; prune3 = 0; prune4 = 0;

            for (var path : paths) {
                if (path.getTotalHeat() > 1012 ) {
                    prune1++;
                    continue;
                }

                if (!results.isEmpty() && results.stream().anyMatch(a -> a.getTotalHeat() < path.getTotalHeat())) {
                    prune2++;
                    continue;
                }

                if (totalSteps > 100 && (totalSteps - path.getPosition().getSumOfCoordinates()) > totalSteps * 0.2){
                    prune3++;
                    continue;
                }


                HeatTile currentTile = grid.getElementAt(path.getPosition());
                if (path.getPosition().equals(targetPosition)) {
//                    path.getPrevious().add(new Position(path.getPosition()));
                    path.addHeat(currentTile.getHeat());
                    results.add(path);
                    continue;
                }

                if (currentTile.getPassedRecords().shouldSkip(path)) {
                    prune4++;
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
            totalSteps++;
            System.out.println("Paths size: " + paths.size());
            System.out.println("First path heat: " + paths.getFirst().getTotalHeat());
            System.out.println("Steps taken: " + totalSteps);
            long prunedThisIteration = prune1 + prune2 + prune3 + prune4;
            System.out.println("percentage pruned this round: " + Math.round((float)prunedThisIteration / (float)paths.size() * 100) + "%");
            System.out.println("***********************");
        }
//        var sortedHeatsAndPaths = new TreeMap<>(results.stream().collect(Collectors.groupingBy(Path::getTotalHeat)));
//        List<ArrayList<Position>> fastestRoutes = sortedHeatsAndPaths.firstEntry().getValue().stream().map(Path::getPrevious).toList();
//
//        for (var fastestRoute : fastestRoutes) {
//            grid.printElements(fastestRoute, "#");
//        }
        return results.stream()
                .map(Path::getTotalHeat)
                .min(Long::compareTo).orElseThrow() - grid.getElementAt(0L, 0L).getHeat();
    }

    private void optimizeGridWithDepthFirstSearches() {
        var results = new ArrayList<Path>();
        List<List<Direction>> randomPaths = generateRandomPaths();
        for (List<Direction> randomPath : randomPaths) {
            var path = new Path(EAST, new Position());
            for (Direction direction : randomPath) {
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

        var minHeat = results.stream().map(Path::getTotalHeat).min(Long::compareTo);

        System.out.println("Grid initialized with a million depth first paths to optimize pruning , most optimal path found: " + minHeat.orElseThrow());
    }

    private List<List<Direction>> generateRandomPaths() {
        List<List<Direction>> randomPaths = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            randomPaths.add(generatePaths());
        }
        addEdgePaths(randomPaths);
        return randomPaths;
    }

    private void addEdgePaths(List<List<Direction>> randomPaths) {
        var southEast = new ArrayList<Direction>();
        for (int i = 0; i < grid.getGridSize(); i++) {
            for (int j = 0; j < 3; j++) {
                southEast.add(SOUTH);
            }
            southEast.add(EAST);
        }
        for (int i = 0; i < grid.getGridSize(); i++) {
            for (int j = 0; j < 3; j++) {
                southEast.add(EAST);
            }
            southEast.add(SOUTH);
        }
        var eastSouth = southEast.subList(0, southEast.size());
        Collections.reverse(eastSouth);
        randomPaths.add(southEast);
        randomPaths.add(eastSouth);
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
