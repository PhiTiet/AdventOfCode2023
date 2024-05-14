package org.example.Solution.day17.model;

import lombok.Builder;

@Builder
public record PassedRecord(long stepsTaken, long totalHeat) {
}
