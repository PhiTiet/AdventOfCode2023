package org.example.Solution.day15.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public class Box {
    private final List<Lens> lenses = new ArrayList<>();
    private final Integer boxNumber;

    public void addLens(Lens lens){
        if (hasNoLensWithLabel(lens.getLabel())){
            lenses.add(lens);
            return;
        }
        Lens relevantLens = lenses.stream().filter(l -> l.getLabel().equals(lens.getLabel())).findFirst().get();
        relevantLens.setFocalLength(lens.getFocalLength());
    }

    public void removeLens(Lens lens){
        if (hasNoLensWithLabel(lens.getLabel())){
            return;
        }
        lenses.removeIf(a -> a.getLabel().equals(lens.getLabel()));
    }

    private boolean hasNoLensWithLabel(String label){
        return lenses.stream().noneMatch(a -> a.getLabel().equals(label));
    }
    public Integer getFocusingPower(){
        return IntStream.range(0, lenses.size())
                .map(i -> ((boxNumber + 1) * (i + 1) * lenses.get(i).getFocalLength()))
                .sum();
    }
}
