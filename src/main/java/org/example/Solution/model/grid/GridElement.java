package org.example.Solution.model.grid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GridElement implements Comparable<GridElement> {
    private Position position;
    private String symbol;

    public GridElement(long x, long y, String symbol) {
        this.position = new Position(x, y);
        this.symbol = symbol;
    }

    @Override
    public int compareTo(GridElement o) {
        return position.compareTo(o.getPosition());
    }

    public long getX() {
        return position.getX();
    }

    public long getY() {
        return position.getY();
    }
}
