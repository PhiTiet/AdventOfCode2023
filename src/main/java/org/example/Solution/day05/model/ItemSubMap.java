package org.example.Solution.day05.model;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.Arrays;

import static org.example.Solution.utils.ArrayListUtils.ArrayListOf;

@AllArgsConstructor
public class ItemSubMap {
    private final Range<Long> source;
    private final Range<Long> destination;

    public ItemSubMap(String line){
        var elements = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
        Long length = elements.get(2);
        destination = Range.of(elements.get(0), elements.get(0) + length - 1);
        source = Range.of(elements.get(1), elements.get(1) + length - 1);
    }

    public boolean canMap(Long input) {
        return source.contains(input);
    }

    public Long map(Long input) {
        return destination.getMinimum() + (input - source.getMinimum());
    }

    public boolean canMap(Range<Long> range) {
        return source.isOverlappedBy(range);
    }
    public Range<Long> map (Range<Long> range){
        var intersect = source.intersectionWith(range);
        return Range.of(intersect.getMinimum() + getDifference(), intersect.getMaximum() + getDifference());
    }

    private long getDifference() {
        return destination.getMinimum() - source.getMinimum();
    }

    public ArrayList<Range<Long>> unmappedValues(Range<Long> current) {
        if (!source.isOverlappedBy(current)){
            return ArrayListOf(current);
        }
        var intersect = source.intersectionWith(current);

        if (isInclusiveMiddleSubset(current, source)){
            return new ArrayList<>();
        }
        if (isMiddleSubset(intersect, current)){
            return ArrayListOf(Range.of(current.getMinimum(), intersect.getMinimum() - 1), Range.of(intersect.getMaximum() + 1, current.getMaximum()));
        }
        if (current.getMinimum().equals(intersect.getMinimum())){
            return ArrayListOf(Range.of(intersect.getMaximum() + 1, current.getMaximum()));
        }
        if (current.getMaximum().equals(intersect.getMaximum())){
            return ArrayListOf(Range.of(current.getMinimum(), intersect.getMinimum() - 1));
        }
        throw new IllegalStateException("bruh");

    }

    private boolean isMiddleSubset(Range<Long> subset, Range<Long> superset){
        return superset.getMinimum() < subset.getMinimum() &&  superset.getMaximum() > subset.getMaximum();
    }
    private boolean isInclusiveMiddleSubset(Range<Long> subset, Range<Long> superset){
        return superset.getMinimum() <= subset.getMinimum() &&  superset.getMaximum() >= subset.getMaximum();
    }
}
