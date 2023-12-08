package org.example.Solution.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListUtils {
    @SafeVarargs
    public static <T> ArrayList<T> ArrayListOf(T... arg) {
        return new ArrayList<>(Arrays.asList(arg));
    }
}
