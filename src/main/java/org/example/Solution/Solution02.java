package org.example.Solution;

import org.example.Solution.model.CubeUpperLimit;
import org.example.Solution.model.FileReader;
import org.example.Solution.model.Game;

import java.util.List;

public class Solution02 {
    private final FileReader fileReader = new FileReader();

    public int solve(String path, CubeUpperLimit cubeUpperLimit){
        var games = getGames(path);
        var solution = 0;
        for (var game : games){
            if (game.possible(cubeUpperLimit)){
                solution += game.getId();
            }
        }
        return solution;
    }


    public int solvePartTwo(String path){
        return getGames(path).stream()
            .map(Game::getCubeUpperLimit)
            .map(CubeUpperLimit::getPower)
            .reduce(0, Integer::sum);
    }

    private List<Game> getGames(String path) {
        return fileReader.getLines(path).stream().map(Solution02::getGame).toList();
    }

    private static Game getGame(String l) {
        String[] gameArgs = l.split(": ");
        var gameNum = Integer.parseInt(gameArgs[0].split(" ")[1]);
        return new Game(gameNum, gameArgs[1]);
    }
}
