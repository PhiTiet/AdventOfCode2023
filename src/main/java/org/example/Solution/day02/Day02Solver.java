package org.example.Solution.day02;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day02.model.CubeUpperLimit;
import org.example.Solution.day02.model.Game;

import java.util.List;

public class Day02Solver extends AbstractDayXXSolver {
    private static final CubeUpperLimit cubeUpperLimit = new CubeUpperLimit(14, 13, 12);

    @Override
    public Long partOneSolution() {
        var games = getGames();
        return games.stream()
                .filter(game -> game.possible(cubeUpperLimit))
                .map(Game::getGameId)
                .reduce(0L, Long::sum);
    }

    @Override
    public Long partTwoSolution() {
        return getGames().stream()
                .map(Game::getCubeUpperLimit)
                .map(CubeUpperLimit::getPower)
                .reduce(0L, Long::sum);
    }

    private List<Game> getGames() {
        return getDefaultPuzzleInputLines().stream().map(Day02Solver::getGame).toList();
    }

    private static Game getGame(String l) {
        String[] gameArgs = l.split(": ");
        var gameNum = Integer.parseInt(gameArgs[0].split(" ")[1]);
        return new Game(gameNum, gameArgs[1]);
    }
}
