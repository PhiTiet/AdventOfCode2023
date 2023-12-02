package org.example;


import org.example.Solution.Day02Solver;
import org.example.Solution.model.CubeUpperLimit;

public class Main {

    public static final String DAY_TWO = "/day02.txt";

    public static void main(String[] args) {
        var day02Solver = new Day02Solver();
        System.out.println(day02Solver.solve(DAY_TWO, new CubeUpperLimit(14, 13, 12)));
        System.out.println(day02Solver.solvePartTwo(DAY_TWO));
    }
}