package org.example.Solution.day16.model;

import org.example.Solution.model.grid.Grid;
import org.example.Solution.model.grid.Position;

import java.util.List;

public class MirrorGrid extends Grid<Mirror> {
    public MirrorGrid(List<String> lines) {
        super(lines, Mirror.class);
    }

    public MirrorType getMirrorTypeAt(Position position){
        return getElementAt(position).getMirrorType();
    }

    public void printEnergized(){
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                Mirror element = elements.get(getIndex(x, y));
                System.out.print((element.isEnergized() ? "#" : ".") + " ");
            }
            System.out.println();
        }
    }
}
