package org.example.Solution.day05.model;

import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.Solution.utils.RegexUtils.SYSTEM_NEWLINE;

public class ItemMapper {
    private final List<ItemSubMap> subMappings;

    public ItemMapper(String inputLine) {
        var lines = inputLine.split(SYSTEM_NEWLINE);
        subMappings = Arrays.stream(lines)
                .map(ItemSubMap::new)
                .toList();
    }

    public Long map(Long input) {
        for (var mapping : subMappings) {
            if (mapping.canMap(input)) {
                return mapping.map(input);
            }
        }
        return input;
    }

    private ArrayList<Range<Long>> map(Range<Long> range, int mapIndex) {
        ArrayList<Range<Long>> ans = new ArrayList<>();
        var currentMap = subMappings.get(mapIndex);
        if (currentMap.canMap(range)) {
            ans.add(currentMap.map(range));
        }
        var remainder = currentMap.unmappedValues(range);
        ans.addAll(multiMap(remainder, mapIndex + 1));
        return ans;
    }

    public ArrayList<Range<Long>> multiMap(ArrayList<Range<Long>> input, int mapIndex) {
        if (mapIndex >= subMappings.size()) {
            return input;
        }
        return input.stream().map(a -> map(a, mapIndex)).reduce(new ArrayList<>(), this::addList);
    }

    private ArrayList<Range<Long>> addList(ArrayList<Range<Long>> a, ArrayList<Range<Long>> b) {
        ArrayList<Range<Long>> result = new ArrayList<>(a);
        result.addAll(b);
        return result;
    }
}
