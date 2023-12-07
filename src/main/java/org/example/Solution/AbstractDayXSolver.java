package org.example.Solution;

import org.example.Solution.utils.FileReader;

public abstract class AbstractDayXSolver implements DayXSolver {
    protected final FileReader fileReader = new FileReader();
    @Override
    public abstract <T> T partOneSolution();

    @Override
    public abstract <T> T partTwoSolution();
}
