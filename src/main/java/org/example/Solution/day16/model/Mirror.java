package org.example.Solution.day16.model;

import lombok.Getter;
import lombok.Setter;
import org.example.Solution.model.grid.GridElement;
@Getter
public class Mirror extends GridElement {
    private final MirrorType mirrorType;
    @Setter
    private boolean energized;
    public Mirror(long x, long y, String symbol) {
        super(x, y, symbol);
        energized = false;
        mirrorType = switch(symbol){
            case "." -> MirrorType.EMPTY;
            case "-" -> MirrorType.HORIZONTAL;
            case "|" -> MirrorType.VERTICAL;
            case "/" -> MirrorType.DIAGONAL_RIGHT_LEFT;
            case "\\" -> MirrorType.DIAGONAL_LEFT_RIGHT;
            default -> throw new IllegalStateException();
        };
    }
}
