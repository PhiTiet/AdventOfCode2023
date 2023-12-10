package org.example.Solution.utils.grid;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final List<GridElement> elements;
    private final int gridSize;

    public Grid(List<String> lines, Class<? extends GridElement> elementClass) {
        elements = new ArrayList<>();
        gridSize = lines.size();
        for (int y = 0; y < gridSize; y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                assert (gridSize == line.length());
                char symbol = line.charAt(x);
                GridElement e = getGridElement(x, y, symbol, elementClass);
                elements.add(e);
            }
        }
    }

    public GridElement getElementAt(long x, long y) {
        return elements.stream()
                .filter(element -> element.getX() == x && element.getY() == y)
                .findFirst().orElse(null);
    }

    public GridElement findWithUniqueSymbol(String symbol) {
        return elements.stream().filter(e -> e.getSymbol().equals(symbol)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public void print() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int index = y * gridSize + x;
                GridElement element = elements.get(index);
                System.out.print(element.getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void printSelectedElements(List<GridElement> selectedElements) {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int index = y * gridSize + x;
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

    
    private GridElement getGridElement(int x, int y, char symbol, Class<? extends GridElement> elementClass) {
        try {
            var constructor = elementClass.getDeclaredConstructor(long.class, long.class, String.class);
            return constructor.newInstance((long) x, (long) y, String.valueOf(symbol));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
