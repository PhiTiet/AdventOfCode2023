package org.example.Solution.model;

public record CubeUpperLimit(int blue, int green, int red) {

    public int getPower(){
        return blue * green * red;
    }
}
