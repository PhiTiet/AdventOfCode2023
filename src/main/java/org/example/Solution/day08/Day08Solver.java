package org.example.Solution.day08;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day08.model.NetworkExecutor;

import java.util.List;

import static org.example.Solution.utils.RegexUtils.SYSTEM_NEWLINE;

public class Day08Solver extends AbstractDayXXSolver<Long> {
    private final NetworkExecutor networkExecutor;

    public Day08Solver() {
        List<String> rawCommandsAndNodes = getDefaultPuzzleInputWithDelimiter(SYSTEM_NEWLINE + SYSTEM_NEWLINE);
        this.networkExecutor = new NetworkExecutor(rawCommandsAndNodes);
    }

    @Override
    public Long partOneSolution() {
        return networkExecutor.execute();
    }

    @Override
    public Long partTwoSolution() {
        return networkExecutor.executeGhost();
    }

}
