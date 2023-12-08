package org.example.Solution.day05.model;

import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

public class MapperChain {
    private final List<ItemMapper> mappers;

    public MapperChain(List<String> blocks) {
        mappers = blocks.stream().map(ItemMapper::new).toList();
    }

    public Long map(Long input) {
        var ret = input;
        for (var mapper : mappers) {
            ret = mapper.map(ret);
        }
        return ret;
    }

    public List<Range<Long>> map(ArrayList<Range<Long>> input) {
        var ret = input;
        for (var mapper : mappers) {
            ret = mapper.multiMap(ret, 0);
        }
        return ret;
    }
}
