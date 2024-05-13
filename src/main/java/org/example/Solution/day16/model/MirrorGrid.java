package org.example.Solution.day16.model;

import org.example.Solution.model.grid.Grid;
import org.example.Solution.model.grid.Position;

import java.util.List;

public class MirrorGrid extends Grid<Mirror> {

    public MirrorType getMirrorTypeAt(Position position){
        return getElementAt(position).getMirrorType();
    }
    public MirrorGrid(List<String> lines) {
        super(lines, Mirror.class);
    }
}
