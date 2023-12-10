package org.example.Solution;

import org.example.Solution.utils.FileReader;

import java.util.List;

public abstract class AbstractDayXXSolver<T> implements DayXXSolver<T> {
    protected final FileReader fileReader = new FileReader();

    @Override
    public void printSolutions() {
        System.out.println("********************* Current Solver: " + this.getClass().getSimpleName() + " *********************");
        System.out.println("Solution part one: " + partOneSolution());
        System.out.println("Solution part two: " + partTwoSolution());
    }

    @Override
    public abstract T partOneSolution();

    @Override
    public abstract T partTwoSolution();

    protected String getPuzzleInputPath() {
        return "/" + this.getClass().getSimpleName().substring(0, 5).toLowerCase() + ".txt";
    }

    protected List<String> getDefaultPuzzleInputLines() {
        return fileReader.getLines(getPuzzleInputPath());
    }

    protected List<String> getDefaultPuzzleInputWithDelimiter(String delimiter) {
        return fileReader.getLines(getPuzzleInputPath(), delimiter);
    }
}
