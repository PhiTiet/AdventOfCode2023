package org.example;


import org.example.Solution.Solution02;
import org.example.Solution.model.CubeUpperLimit;

public class Main {
    public static void main(String[] args) {
        var solution02 = new Solution02();
        System.out.println(solution02.solve("/day02.txt", new CubeUpperLimit(14, 13, 12)));
        System.out.println(solution02.solvePartTwo("/day02.txt"));
    }
}