package org.example.Solution.utils;

public class LongUtils {
    //credit to https://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers
    public static long least_common_multiple(long a, long b) {
        return a * (b / greatest_common_divider(a, b));
    }

    private static long greatest_common_divider(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
