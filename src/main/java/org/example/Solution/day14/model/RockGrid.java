package org.example.Solution.day14.model;

import org.example.Solution.model.grid.Grid;

import java.util.List;

import static org.example.Solution.day14.model.RockType.*;

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

}
