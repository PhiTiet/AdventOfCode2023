package org.example.Solution.day17.model;

import lombok.Builder;
import org.example.Solution.utils.model.Direction;

@Builder
public record PassedRecord(long stepsTaken, long totalHeat, Direction direction) {
}
