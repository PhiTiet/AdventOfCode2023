package org.example.Solution.model.grid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class GridElement {
    private long x;
    private long y;
    private String symbol;
}
