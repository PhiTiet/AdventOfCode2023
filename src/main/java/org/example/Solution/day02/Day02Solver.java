package org.example.Solution.day02;

import org.example.Solution.day02.model.CubeUpperLimit;
import org.example.Solution.day02.model.Game;
import org.example.Solution.utils.FileReader;

import java.util.List;

public class Day02Solver {
    private final FileReader fileReader = new FileReader();
    private static final String PATH = "/day02.txt";
    private static final CubeUpperLimit cubeUpperLimit = new CubeUpperLimit(14, 13, 12);

    public void solvePartOne() {
        var games = getGames();
        var answer = games.stream()
                .filter(game ->  game.possible(cubeUpperLimit))
                .map(Game::getGameId)
                .reduce(0, Integer::sum);
        System.out.println(answer);
    }


    public void solvePartTwo() {
        var answer = getGames().stream()
                .map(Game::getCubeUpperLimit)
                .map(CubeUpperLimit::getPower)
                .reduce(0, Integer::sum);
        System.out.println(answer);
    }

    private List<Game> getGames() {
        return fileReader.getLines(PATH).stream().map(Day02Solver::getGame).toList();
    }

    private static Game getGame(String l) {
        String[] gameArgs = l.split(": ");
        var gameNum = Integer.parseInt(gameArgs[0].split(" ")[1]);
        return new Game(gameNum, gameArgs[1]);
    }
}
