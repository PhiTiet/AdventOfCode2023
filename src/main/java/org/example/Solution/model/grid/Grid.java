package org.example.Solution.model.grid;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
public class Grid<E extends GridElement> {
    protected List<E> elements;
    protected final int gridSize;

    public Grid(List<String> lines, Class<E> elementClass) {
        elements = new ArrayList<>();
        gridSize = lines.size();
        for (int y = 0; y < gridSize; y++) {
            String line = lines.get(y);
            for (int x = 0; x < gridSize; x++) {
                assert (gridSize == line.length());
                char symbol = line.charAt(x);
                E element = getGridElement(x, y, symbol, elementClass);
                elements.add(element);
            }
        }
    }

    public E getElementAt(long x, long y) {
        return elements.get(getIndex((int) x, (int) y));
    }
    public void setElementAt(long x, long y, E type){
        elements.set(getIndex((int) x, (int) y), type);
    }

    public List<E> getElementsWhere(Predicate<E> filterFunction) {
        return elements.stream().filter(filterFunction).toList();
    }

    public void print() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int index = getIndex(x, y);
                GridElement element = elements.get(index);
                System.out.print(element.getSymbol() + " ");
            }
            System.out.println();
        }
    }


    protected int getIndex(int x, int y) {
        return y * gridSize + x;
    }

    private E getGridElement(int x, int y, char symbol, Class<E> elementClass) {
        try {
            var constructor = elementClass.getDeclaredConstructor(long.class, long.class, String.class);
            return constructor.newInstance((long) x, (long) y, String.valueOf(symbol));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public void rotate90Clockwise() {
        for (int i = 0; i < gridSize / 2; i++) {
            for (int j = i; j < gridSize - i - 1; j++) {
                var temp = getElementAt(i,j);
                int n1j = gridSize - 1 - j;
                int n1i = gridSize - 1 - i;
                setElementAt(i,j, getElementAt(n1j, i));
                setElementAt(n1j, i, getElementAt(n1i, n1j));
                setElementAt(n1i, n1j, getElementAt(j, n1i));
                setElementAt(j, n1i, temp);
            }
        }
    }

}
