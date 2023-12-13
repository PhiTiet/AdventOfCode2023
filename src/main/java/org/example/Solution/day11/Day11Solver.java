package org.example.Solution.day11;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;
import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day11.model.Space;
import org.example.Solution.day11.model.SpaceGrid;
import org.example.Solution.day11.model.SpaceType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day11Solver extends AbstractDayXXSolver<Long> {
    private final SpaceGrid grid;
    private final List<Long> doubleSpaceXIndices;
    private final List<Long> doubleSpaceYIndices;

    public Day11Solver() {
        this.grid = new SpaceGrid(getDefaultPuzzleInputLines());
        doubleSpaceXIndices = grid.doubleSpaceXIndices();
        doubleSpaceYIndices = grid.doubleSpaceYIndices();
    }

    @Override
    public Long partOneSolution() {
        List<Space> galaxies = grid.getElementsWhere(a -> a.getSpaceType().equals(SpaceType.GALAXY));
        Set<Pair<Space, Space>> pairs = generatePairSet(galaxies);
        List<Long> distances = pairs.stream().map(a -> distanceBetween(a.getLeft(), a.getRight(), 2L)).toList();
        return distances.stream().reduce(0L, Long::sum);
    }

    @Override
    public Long partTwoSolution() {
        List<Space> galaxies = grid.getElementsWhere(a -> a.getSpaceType().equals(SpaceType.GALAXY));
        Set<Pair<Space, Space>> pairs = generatePairSet(galaxies);
        List<Long> distances = pairs.stream().map(a -> distanceBetween(a.getLeft(), a.getRight(), 1000000L)).toList();
        return distances.stream().reduce(0L, Long::sum);
    }

    private Long distanceBetween(Space a, Space b, long spaceExpansion) {
        Range<Long> xRange = getRange(a.getX(), b.getX());
        Range<Long> yRange = getRange(a.getY(), b.getY());
        long extraXDistance = doubleSpaceXIndices.stream().filter(xRange::contains).count() * (spaceExpansion - 1);
        long extraYDistance = doubleSpaceYIndices.stream().filter(yRange::contains).count() * (spaceExpansion - 1);
        return distanceBetweenSpaces(a, b) + extraXDistance + extraYDistance;
    }

    private static long distanceBetweenSpaces(Space a, Space b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private Range<Long> getRange(long x, long x1) {
        return Range.of(Math.min(x, x1), Math.max(x, x1));
    }

    private Set<Pair<Space, Space>> generatePairSet(List<Space> galaxies) {
        Set<Pair<Space, Space>> pairs = new HashSet<>();

        for (int i = 0; i < galaxies.size(); i++) {
            Space first = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                Space second = galaxies.get(j);
                pairs.add(Pair.of(first, second));
            }
        }
        return pairs;
    }

}
