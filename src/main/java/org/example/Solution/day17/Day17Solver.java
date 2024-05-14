package org.example.Solution.day17;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day17.model.HeatGrid;
import org.example.Solution.day17.model.HeatTile;
import org.example.Solution.day17.model.PassedRecord;
import org.example.Solution.day17.model.Path;
import org.example.Solution.model.grid.Position;
import org.example.Solution.utils.ArrayListUtils;


import java.util.ArrayList;
import java.util.List;
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
        var newPaths = new ArrayList<Path>();
        var results = new ArrayList<Path>();
        while (!paths.isEmpty()) {
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

                if (currentTile.getPassedRecords().shouldSkip(path)){
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
        List<Position> fastestRoute = sortedHeatsAndPaths.firstEntry().getValue().getFirst().getPrevious();


        grid.printElements(fastestRoute, "#");

        return results.stream()
                .map(Path::getTotalHeat)
                .min(Long::compareTo).orElseThrow() - grid.getElementAt(0L, 0L).getHeat();
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
