package org.example.Solution.utils;

import java.util.List;

public class StringUtils {
    public static List<String> toCharacterList(String str){
        return str.chars().mapToObj(s -> String.valueOf((char) s)).toList();
    }
}
