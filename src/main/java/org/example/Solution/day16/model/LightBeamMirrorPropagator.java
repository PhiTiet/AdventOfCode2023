package org.example.Solution.day16.model;

import org.example.Solution.model.grid.Position;

import java.util.ArrayList;

import static org.example.Solution.utils.ArrayListUtils.ArrayListOf;
import static org.example.Solution.utils.model.Direction.*;

public class LightBeamMirrorPropagator {
    public ArrayList<LightBeam> resultingLightBeams(MirrorType mirrorType, LightBeam lightBeam) {
        var currentDirection = lightBeam.getDirection();
        var currentPosition = lightBeam.getPosition();

        ArrayList<LightBeam> unaffected = ArrayListOf(lightBeam);
        boolean goingVertically = currentDirection == NORTH || currentDirection == SOUTH;

        return switch (mirrorType) {
            case EMPTY -> unaffected;
            //"\"
            case DIAGONAL_LEFT_RIGHT -> switch (currentDirection) {
                case NORTH -> ArrayListOf(new LightBeam(WEST, currentPosition));
                case EAST -> ArrayListOf(new LightBeam(SOUTH, currentPosition));
                case SOUTH -> ArrayListOf(new LightBeam(EAST, currentPosition));
                case WEST -> ArrayListOf(new LightBeam(NORTH, currentPosition));
            };
            //"/"
            case DIAGONAL_RIGHT_LEFT -> switch (currentDirection) {
                case NORTH -> ArrayListOf(new LightBeam(EAST, currentPosition));
                case EAST -> ArrayListOf(new LightBeam(NORTH, currentPosition));
                case SOUTH -> ArrayListOf(new LightBeam(WEST, currentPosition));
                case WEST -> ArrayListOf(new LightBeam(SOUTH, currentPosition));
            };

            case VERTICAL -> {
                if (goingVertically) {
                    yield unaffected;
                }
                yield ArrayListOf(new LightBeam(NORTH, new Position(currentPosition)), new LightBeam(SOUTH, new Position(currentPosition)));
            }
            case HORIZONTAL -> {
                if (!goingVertically) {
                    yield unaffected;
                }
                yield ArrayListOf(new LightBeam(WEST, new Position(currentPosition)), new LightBeam(EAST, new Position(currentPosition)));
            }
        };
    }
}
