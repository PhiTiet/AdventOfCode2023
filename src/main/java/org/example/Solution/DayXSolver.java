package org.example.Solution;

public interface DayXSolver {
    <T> T solvePartOne();
    <T> T solvePartTwo();

    default void printSolutions(){
        System.out.println("********************* Current Solver:" + this.getClass().getSimpleName() + " *********************");
        System.out.println("Solution part one: " + solvePartOne());
        System.out.println("Solution part two: " + solvePartTwo());
    }
}
