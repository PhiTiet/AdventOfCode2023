package org.example.Solution.day08.model;

import lombok.Getter;

@Getter
public class NetworkNode {
    String name;
    String left;
    String right;

    public NetworkNode(String line) {
        name = line.substring(0, 3);
        left = line.substring(7, 10);
        right = line.substring(12, 15);
    }
}
