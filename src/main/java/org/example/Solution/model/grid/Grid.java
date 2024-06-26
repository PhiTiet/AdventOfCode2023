package org.example.Solution.model.grid;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
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

    public E getElementAt(Position position) {
        return getElementAt(position.getX(), position.getY());
    }

    public void setElementAt(Position position, E type) {
        setElementAt(position.getX(), position.getY(), type);
    }

    public E getElementAt(long x, long y) {
        return elements.get(getIndex((int) x, (int) y));
    }

    public void setElementAt(long x, long y, E type) {
        elements.set(getIndex((int) x, (int) y), type);
    }

    public List<E> getElementsWhere(Predicate<E> filterFunction) {
        return elements.stream().filter(filterFunction).toList();
    }

    public void print() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                GridElement element = elements.get(getIndex(x, y));
                System.out.print(element.getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void printElements(List<Position> positions, String s) {
        System.out.println("---------------------------");
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                GridElement element = elements.get(getIndex(x, y));
                var contains = positions.stream().anyMatch(a -> a.getX() == element.getX() && a.getY() == element.getY());
                System.out.print((contains ? s : element.getSymbol()) + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public void sortElements() {
        Collections.sort(elements);
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

}
