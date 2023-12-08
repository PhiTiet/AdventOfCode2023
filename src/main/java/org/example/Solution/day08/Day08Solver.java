package org.example.Solution.day08;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day08.model.NetworkExecutor;

import java.util.List;

import static org.example.Solution.utils.RegexUtils.WINDOWS_NEWLINE;

public class Day08Solver extends AbstractDayXXSolver {
    private final List<String> rawCommandsAndNodes = getDefaultPuzzleInputWithDelimiter(WINDOWS_NEWLINE + WINDOWS_NEWLINE);

    @Override
    public Long partOneSolution() {
        var networkExecutor = getNetworkExecutor();
        return networkExecutor.execute();
    }

    @Override
    public Long partTwoSolution() {
        var networkExecutor = getNetworkExecutor();
        return networkExecutor.executeGhost();
    }

    private NetworkExecutor getNetworkExecutor() {
        return new NetworkExecutor(rawCommandsAndNodes);
    }
}
