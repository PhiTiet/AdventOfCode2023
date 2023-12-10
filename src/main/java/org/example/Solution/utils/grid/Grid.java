package org.example.Solution.utils.grid;

import java.util.ArrayList;
import java.util.List;

public class Grid<E extends GridElement> {
    private final List<E> elements;
    private final int gridSize;

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
        return elements.stream()
                .filter(element -> element.getX() == x && element.getY() == y)
                .findFirst().orElse(null);
    }

    public void print() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int index = getIndex(y, x);
                GridElement element = elements.get(index);
                System.out.print(element.getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void printSelectedElements(List<GridElement> selectedElements) {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int index = getIndex(y, x);
                GridElement element = elements.get(index);
                if (selectedElements.contains(element)) {
                    System.out.print(element.getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private int getIndex(int y, int x) {
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
}
