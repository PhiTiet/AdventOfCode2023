package org.example.Solution.model.grid;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GridElement {
    private long x;
    private long y;
    private String symbol;
}
