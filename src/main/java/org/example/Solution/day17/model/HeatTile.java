package org.example.Solution.day17.model;

import lombok.Getter;
import lombok.Setter;
import org.example.Solution.model.grid.GridElement;
import org.example.Solution.utils.model.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class HeatTile extends GridElement {
    private final long heat;
    private ArrayList<PassedRecord> northPassedRecords;
    private ArrayList<PassedRecord> southPassedRecords;
    private ArrayList<PassedRecord> eastPassedRecords;
    private ArrayList<PassedRecord> westPassedRecords;



    public HeatTile(long x, long y, String symbol) {
        super(x, y, symbol);
        heat = Long.parseLong(symbol);
        southPassedRecords = new ArrayList<>();
        eastPassedRecords = new ArrayList<>();
        westPassedRecords = new ArrayList<>();
        northPassedRecords = new ArrayList<>();

    }

    public ArrayList<PassedRecord> getPassedRecordsForDirection(Direction d){
        return switch(d){
            case NORTH -> northPassedRecords;
            case EAST -> eastPassedRecords;
            case SOUTH -> southPassedRecords;
            case WEST -> westPassedRecords;
        };
    }
}
