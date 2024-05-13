package org.example.Solution.day16;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day16.model.LightBeam;
import org.example.Solution.day16.model.Mirror;
import org.example.Solution.day16.model.MirrorGrid;
import org.example.Solution.model.grid.Grid;
import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;

import static org.example.Solution.utils.ArrayListUtils.ArrayListOf;

public class Day16Solver extends AbstractDayXXSolver<Long> {
    private final Grid<Mirror> grid = new MirrorGrid(getDefaultPuzzleInputLines());

    private LightBeam initalLightbeam = new LightBeam(Direction.EAST, 0 ,0);
    private ArrayList<LightBeam> lightbeams = ArrayListOf(initalLightbeam);
    private ArrayList<LightBeam> newLightBeams = new ArrayList<>();

    @Override
    public Long partOneSolution() {
        for (var lightBeam: lightbeams){

        }
        grid.print();
        return null;
    }

    @Override
    public Long partTwoSolution() {
        return null;
    }
}
