package org.example;

import org.example.Solution.day01.Day01Solver;
import org.example.Solution.day02.Day02Solver;
import org.example.Solution.day04.Day04Solver;
import org.example.Solution.day05.Day05Solver;
import org.example.Solution.day06.Day06Solver;
import org.example.Solution.day07.Day07Solver;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {
    @Nested
    class StillCorrect{

        @Test
        void dayOne(){
            var day01 = new Day01Solver();
            assertThat(day01.partOneSolution()).isEqualTo(54331L);
            assertThat(day01.partTwoSolution()).isEqualTo(54518L);
        }

        @Test
        void dayTwo(){
            var day02 = new Day02Solver();
            assertThat(day02.partOneSolution()).isEqualTo(2256L);
            assertThat(day02.partTwoSolution()).isEqualTo(74229L);
        }

        @Test
        void dayFour(){
            var day04 = new Day04Solver();
            assertThat(day04.partOneSolution()).isEqualTo(22193L);
            assertThat(day04.partTwoSolution()).isEqualTo(5625994L);
        }

        @Test
        void dayFive(){
            var day05 = new Day05Solver();
            assertThat(day05.partOneSolution()).isEqualTo(403695602L);
            assertThat(day05.partTwoSolution()).isEqualTo(219529182L);
        }

        @Test
        void daySix(){
            var day06 = new Day06Solver();
            assertThat(day06.partOneSolution()).isEqualTo(114400L);
            assertThat(day06.partTwoSolution()).isEqualTo(21039729L);
        }

        @Test
        void daySeven(){
            var day07 = new Day07Solver();
            assertThat(day07.partOneSolution()).isEqualTo(250951660L);
            assertThat(day07.partTwoSolution()).isEqualTo(251481660L);
        }
    }

}