package org.example;


import org.example.Solution.DayXSolver;
import org.example.Solution.day02.Day02Solver;
import org.example.Solution.day04.Day04Solver;
import org.example.Solution.day05.Day05Solver;
import org.example.Solution.day06.Day06Solver;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<DayXSolver> solvers = List.of(new Day02Solver(), new Day04Solver(), new Day05Solver(), new Day06Solver());
        for (var solver : solvers){
            solver.printSolutions();
        }

    }
}