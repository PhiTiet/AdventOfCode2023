package org.example;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day01.Day01Solver;
import org.example.Solution.day02.Day02Solver;
import org.example.Solution.day04.Day04Solver;
import org.example.Solution.day05.Day05Solver;
import org.example.Solution.day06.Day06Solver;
import org.example.Solution.day07.Day07Solver;
import org.example.Solution.day08.Day08Solver;
import org.example.Solution.day09.Day09Solver;
import org.example.Solution.day10.Day10Solver;
import org.example.Solution.day11.Day11Solver;
import org.example.Solution.day12.Day12Solver;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {
    @Nested
    class StillCorrect {

        @Test
        void dayOne() {
            correctLongSolutions(new Day01Solver(), 54331L, 54518L);
        }

        @Test
        void dayTwo() {
            correctLongSolutions(new Day02Solver(), 2256L, 74229L);
        }

        @Test
        void dayFour() {
            correctLongSolutions(new Day04Solver(), 22193L, 5625994L);
        }

        @Test
        void dayFive() {
            correctLongSolutions(new Day05Solver(), 403695602L, 219529182L);
        }

        @Test
        void daySix() {
            correctLongSolutions(new Day06Solver(), 114400L, 21039729L);
        }

        @Test
        void daySeven() {
            correctLongSolutions(new Day07Solver(), 250951660L, 251481660L);
        }

        @Test
        void dayEight() {
            correctLongSolutions(new Day08Solver(), 19951L, 16342438708751L);
        }

        @Test
        void dayNine() {
            correctLongSolutions(new Day09Solver(), 1637452029L, 908L);
        }

        @Test
        void dayTen() {
            correctLongSolutions(new Day10Solver(), 6864L, 349L);
        }

        @Test
        void dayEleven() {
            correctLongSolutions(new Day11Solver(), 9681886L, 791134099634L);
        }

        @Test
        void dayTwelve() {correctLongSolutions(new Day12Solver(), 0L, null);}

        void correctLongSolutions(AbstractDayXXSolver<Long> solver, Long answerPartOne, Long answerPartTwo) {
            assertThat(solver.partOneSolution()).isEqualTo(answerPartOne);
            assertThat(solver.partTwoSolution()).isEqualTo(answerPartTwo);
        }

    }

}