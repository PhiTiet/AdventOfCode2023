package org.example.Solution.day16.model;

import lombok.Getter;
import org.example.Solution.model.grid.GridElement;
import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;

@Getter
public class Mirror extends GridElement {
    private final MirrorType mirrorType;
    private ArrayList<Direction> energizedBy;

    public Mirror(long x, long y, String symbol) {
        super(x, y, symbol);
        energizedBy = new ArrayList<>();
        mirrorType = switch(symbol){
            case "." -> MirrorType.EMPTY;
            case "-" -> MirrorType.HORIZONTAL;
            case "|" -> MirrorType.VERTICAL;
            case "/" -> MirrorType.DIAGONAL_RIGHT_LEFT;
            case "\\" -> MirrorType.DIAGONAL_LEFT_RIGHT;
            default -> throw new IllegalStateException();
        };
    }
    public boolean isEnergized(){
        return !energizedBy.isEmpty();
    }
}
