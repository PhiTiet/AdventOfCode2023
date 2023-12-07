package org.example.Solution.day02.model;

public record CubeUpperLimit(long blue, long green, long red) {
    public long getPower() {
        return blue * green * red;
    }
}
