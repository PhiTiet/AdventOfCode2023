package org.example.Solution.model.grid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Position implements Comparable<Position> {
    private long x;
    private long y;

    public void incrementY(){
        y++;
    }
    public void decrementY(){
        y--;
    }
    public void incrementX(){
        x++;
    }
    public void decrementX(){
        x--;
    }
        @Override
        public int compareTo(Position other) {
            if (y == other.y) {
                return Long.compare(x, other.x);
            }
            return Long.compare(y, other.y);
        }
}
