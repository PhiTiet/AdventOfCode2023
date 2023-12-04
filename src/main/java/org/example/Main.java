package org.example;


import org.example.Solution.day02.Day02Solver;
import org.example.Solution.day04.Day04Solver;

public class Main {

    public static void main(String[] args) {

        var day02Solver = new Day02Solver();
        day02Solver.solvePartOne();
        day02Solver.solvePartTwo();

        var day04Solver = new Day04Solver();
        day04Solver.solvePartOne();
        day04Solver.solvePartTwo();

    }
}