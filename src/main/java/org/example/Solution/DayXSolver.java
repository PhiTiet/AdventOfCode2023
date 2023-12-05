package org.example.Solution;

public interface DayXSolver {
    //TODO INTRODUCE SOLUTION CLASS WITH GENERIC TYPE ANSWER & PRINT FUNCTION
    <T> T partOneSolution();
    <T> T partTwoSolution();

    default void printSolutions(){
        System.out.println("********************* Current Solver: " + this.getClass().getSimpleName() + " *********************");
        System.out.println("Solution part one: " + partOneSolution());
        System.out.println("Solution part two: " + partTwoSolution());
    }
}
