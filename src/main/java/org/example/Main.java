package org.example;


import org.example.Solution.DayXSolver;
import org.example.Solution.day02.Day02Solver;
import org.example.Solution.day04.Day04Solver;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<DayXSolver> solvers = List.of(new Day02Solver(), new Day04Solver());

        for (var solver : solvers){
            solver.printSolutions();
       }

    }
}