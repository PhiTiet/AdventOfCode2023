package org.example.Solution.model.grid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class GridElement implements Comparable<GridElement> {
    private long x;
    private long y;
    private String symbol;

    @Override
    public int compareTo(GridElement other) {
        if (y == other.y){
            return Long.compare(x, other.getX());
        };
        return Long.compare(y, other.y);
    }
}
