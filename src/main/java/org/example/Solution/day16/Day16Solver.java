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
    private final MirrorGrid grid = new MirrorGrid(getDefaultPuzzleInputLines());
    private final LightBeamMirrorPropagator lightbeamMirrorPropogator = new LightBeamMirrorPropagator();

    private LightBeam initalLightbeam = new LightBeam(Direction.SOUTH, new Position(0, 0));
    private ArrayList<LightBeam> lightbeams = ArrayListOf(initalLightbeam);
    private ArrayList<LightBeam> newLightBeams = new ArrayList<>();

    @Override
    public Long partOneSolution() {
        propagateLightBeamsAndEnergizeGrid();
//        grid.printEnergized();
        return grid.getElements().stream().filter(Mirror::isEnergized).count();
    }

    private void propagateLightBeamsAndEnergizeGrid() {
        do {
            for (var lightBeam : lightbeams) {
                var energizedBy = grid.getElementAt(lightBeam.getPosition()).getEnergizedBy();

                if (energizedBy.contains(lightBeam.getDirection())){
                    continue;
                }
                energizedBy.add(lightBeam.getDirection());

                lightBeam.travel();
                Position nextPosition = lightBeam.getPosition();
                if ((nextPosition.getX()) >= grid.getGridSize()  || (nextPosition.getY()) >= grid.getGridSize()
                        || nextPosition.getX() < 0 || nextPosition.getY() < 0) {
                    continue;
                }
                var nextMirror = grid.getElementAt(nextPosition).getMirrorType();
                var nextLightBeams = lightbeamMirrorPropogator.resultingLightBeams(nextMirror, lightBeam);
                newLightBeams.addAll(nextLightBeams);
            }
            
            lightbeams = newLightBeams;
            newLightBeams = new ArrayList<>();
        } while (!lightbeams.isEmpty());
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
