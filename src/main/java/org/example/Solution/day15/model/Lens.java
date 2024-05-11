package org.example.Solution.day15.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Lens {
    @Setter
    private int focalLength;
    private String label;
}
