package org.example.Solution.day15.model;

public class ElfHasher {
    public Integer hashString(String unhashed){
        int curr = 0;
        for (int i = 0; i < unhashed.length(); i++) {
            int currentASCII = unhashed.charAt(i);
            curr += currentASCII;
            curr *= 17;
            curr = curr % 256;
        }
        return curr;
    }
}
