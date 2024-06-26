package org.example.Solution.day16;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day16.model.LightBeam;
import org.example.Solution.day16.model.LightBeamMirrorPropagator;
import org.example.Solution.day16.model.Mirror;
import org.example.Solution.day16.model.MirrorGrid;
import org.example.Solution.model.grid.Position;
import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;

import static org.example.Solution.utils.ArrayListUtils.ArrayListOf;

public class Day16Solver extends AbstractDayXXSolver<Long> {
    private MirrorGrid grid;
    private final LightBeamMirrorPropagator lightBeamMirrorPropagator = new LightBeamMirrorPropagator();

    @Override
    public Long partOneSolution() {
        return getEnergizedCountAfterPropagatingLight(ArrayListOf(new LightBeam(Direction.SOUTH, new Position(0, 0))));
    }

    private Long getEnergizedCountAfterPropagatingLight(ArrayList<LightBeam> lightBeams) {
        ArrayList<LightBeam> newLightBeams = new ArrayList<>();
        grid = new MirrorGrid(getDefaultPuzzleInputLines());
        while (!lightBeams.isEmpty()) {
            for (var lightBeam : lightBeams) {
                var energizedBy = grid.getElementAt(lightBeam.getPosition()).getEnergizedBy();

                if (energizedBy.contains(lightBeam.getDirection())) {
                    continue;
                }
                energizedBy.add(lightBeam.getDirection());

                lightBeam.travel();
                Position nextPosition = lightBeam.getPosition();
                if ((nextPosition.getX()) >= grid.getGridSize() || (nextPosition.getY()) >= grid.getGridSize()
                        || nextPosition.getX() < 0 || nextPosition.getY() < 0) {
                    continue;
                }
                var nextMirror = grid.getElementAt(nextPosition).getMirrorType();
                var nextLightBeams = lightBeamMirrorPropagator.resultingLightBeams(nextMirror, lightBeam);
                newLightBeams.addAll(nextLightBeams);
            }

            lightBeams = newLightBeams;
            newLightBeams = new ArrayList<>();
        }
        return grid.getElements().stream().filter(Mirror::isEnergized).count();
    }

    @Override
    public Long partTwoSolution() {
        var initialLightBeams = new ArrayList<LightBeam>();
        int gridSize = grid.getGridSize();

        for (int i = 0; i < gridSize; i++) {
            initialLightBeams.add(new LightBeam(Direction.SOUTH, new Position(i, 0)));
            initialLightBeams.add(new LightBeam(Direction.NORTH, new Position(i, gridSize - 1)));
            initialLightBeams.add(new LightBeam(Direction.WEST, new Position(gridSize - 1, i)));
            initialLightBeams.add(new LightBeam(Direction.EAST, new Position(0, i)));
        }
        return initialLightBeams.stream()
                .map(b -> getEnergizedCountAfterPropagatingLight(ArrayListOf(b)))
                .max(Long::compareTo)
                .orElseThrow();
    }

}
