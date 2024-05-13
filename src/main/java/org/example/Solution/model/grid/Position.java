package org.example.Solution.model.grid;

public record Position(long x, long y) implements Comparable<Position> {
        @Override
        public int compareTo(Position other) {
            if (y == other.y) {
                return Long.compare(x, other.x);
            }
            return Long.compare(y, other.y);
        }
}
