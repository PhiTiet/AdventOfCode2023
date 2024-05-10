package org.example.Solution.day14.model;

import org.example.Solution.model.grid.Grid;

import java.util.List;

import static org.example.Solution.day14.model.RockType.EMPTY;
import static org.example.Solution.day14.model.RockType.ROLLING_ROCK;

public class RockGrid extends Grid<Rock> {
    public RockGrid(List<String> lines) {
        super(lines, Rock.class);
    }

    public void slideNorth(){
        boolean rocksRolled;
        do{
            rocksRolled = false;
            for(Rock rock : elements){
                if (rock.getRockType() != ROLLING_ROCK || rock.getY() == 0){
                    continue;
                }
                Rock rockAbove = getElementAt(rock.getX(), rock.getY() - 1);
                if (rockAbove.getRockType() == EMPTY){
                    setElementAt(rock.getX(), rock.getY() - 1, new Rock(rock.getX(), rock.getY() -1, "O"));
                    setElementAt(rock.getX(), rock.getY() , new Rock(rock.getX(), rock.getY(), "."));
                    rocksRolled = true;
                }
            }
        }while (rocksRolled);
    }

    public Long getScore(){
        return elements.stream()
                .filter(rock -> rock.getRockType() == ROLLING_ROCK)
                .mapToLong(rock -> gridSize - rock.getY())
                .sum();
    }


    public void rotate90Clockwise() {
        for (int i = 0; i < gridSize / 2; i++) {
            for (int j = i; j < gridSize - i - 1; j++) {
                var temp = getElementAt(i,j);
                int n1j = gridSize - 1 - j;
                int n1i = gridSize - 1 - i;
                setElementAt(i,j, new Rock(i,j,getElementAt(n1j, i).getSymbol()));
                setElementAt(n1j, i, new Rock(n1j, i, getElementAt(n1i, n1j).getSymbol()));
                setElementAt(n1i, n1j, new Rock(n1i, n1j,getElementAt(j, n1i).getSymbol()));
                setElementAt(j, n1i, new Rock(j,n1i,temp.getSymbol()));
            }
        }
    }

    public void rotate90CounterClockwise(){
        rotate90Clockwise();
        rotate90Clockwise();
        rotate90Clockwise();
    }

//    private void sortElements(){
//        Collections.sort(elements, (e1, e2) -> {
//            // First, compare by y
//            int yComparison = Integer.compare((int)e1.getY(), (int)e2.getY());
//            if (yComparison != 0) {
//                return yComparison;
//            } else {
//                // If y is equal, compare by x
//                return Integer.compare((int)e1.getX(), (int)e2.getX());
//            }
//        });
//    }

}
