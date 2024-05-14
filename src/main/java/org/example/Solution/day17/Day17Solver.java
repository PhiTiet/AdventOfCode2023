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
import java.util.stream.Collectors;


import static org.example.Solution.utils.model.Direction.*;

public class Day17Solver extends AbstractDayXXSolver<Long> {
    private HeatGrid grid = new HeatGrid(getDefaultPuzzleInputLines());
    private final Position targetPosition = new Position(grid.getGridSize() -1, grid.getGridSize() -1);

    @Override
    public Long partOneSolution() {
        long pathHeuristic = 3 * grid.getElementsWhere(a -> a.getX() == 0 || a.getY() == grid.getGridSize() -1)
                .stream()
                .map(HeatTile::getHeat)
                .reduce(0L, Long::sum);

        var path = traverseGrid(ArrayListUtils.ArrayListOf(new Path(EAST, new Position())), pathHeuristic);
        var minWithInitialSquare = path.stream().min(Long::compareTo).orElseThrow();

        return minWithInitialSquare - grid.getElementAt(0L,0L).getHeat();
    }

    private List<Long> traverseGrid(ArrayList<Path> paths, long pathHeuristic){
        var newPaths = new ArrayList<Path>();
        var results = new ArrayList<Path>();
        while(!paths.isEmpty()){
            for (var path : paths){
                if ((path.getTotalHeat() > pathHeuristic)){
                    continue;
                }
                if (!results.isEmpty() && results.stream().anyMatch(a -> a.getTotalHeat() < path.getTotalHeat())){
                    continue;
                }

                HeatTile currentTile = grid.getElementAt(path.getPosition());
                if (path.getPosition().equals(targetPosition)){
                    path.addToTotal(currentTile.getHeat());
                    path.getPrevious().add(currentTile.getPosition());
                    results.add(path);
                    continue;
                }

                var passedRecords = currentTile.getPassedRecordsForDirection(path.getDirection());
                if (!passedRecords.isEmpty()){
                    if(passedRecords.stream().anyMatch(a -> a.totalHeat() <= path.getTotalHeat() && a.stepsTaken() <= path.getStepsTaken())){
                        continue;
                    };
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
        var test = results.stream().filter(a -> a.getTotalHeat() == 1012).findFirst().get();
        for (var t : test.getPrevious()){
            grid.getElementAt(t).setSymbol("#");
        }
        grid.print();

        return results.stream().map(Path::getTotalHeat).toList();
    }

    private void travel(Path path, HeatTile currentTile, ArrayList<Path> newPaths) {
        if (!path.canMoveForward() || path.willBeOutOfRange(grid.getGridSize())){
            return;
        }

        PassedRecord passedRecord = PassedRecord.builder()
                .direction(path.getDirection())
                .stepsTaken(path.getStepsTaken())
                .totalHeat(path.getTotalHeat())
                .build();

        currentTile.getPassedRecordsForDirection(path.getDirection()).add(passedRecord);
        path.travel();
        path.addToTotal(currentTile.getHeat());
        path.getPrevious().add(currentTile.getPosition());
        newPaths.add(path);
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
