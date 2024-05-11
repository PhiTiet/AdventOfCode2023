package org.example.Solution.day15;

import org.example.Solution.AbstractDayXXSolver;
import org.example.Solution.day15.model.Box;
import org.example.Solution.day15.model.BoxInstructionType;
import org.example.Solution.day15.model.ElfHasher;
import org.example.Solution.day15.model.Instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day15Solver extends AbstractDayXXSolver<Integer> {
    private final List<String> unhashed = Arrays.stream(getDefaultPuzzleInputLines().get(0).split(",")).toList();
    private final ElfHasher elfHasher = new ElfHasher();
    private final List<Box> boxes = new ArrayList<>();

    @Override
    public Integer partOneSolution() {
        return unhashed.stream()
                .map(elfHasher::hashString)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer partTwoSolution() {
        initializeBoxes();
        executeInstructions();
        return boxes.stream().map(Box::getFocusingPower).reduce(0, Integer::sum);
    }

    private void executeInstructions() {
        List<Instruction> instructions = unhashed.stream().map(Instruction::new).toList();
        for (var instruction : instructions){
            Box relevantBox = boxes.stream().filter(b -> b.getBoxNumber().equals(instruction.getTargetBoxNumber())).findFirst().get();
            if (instruction.getBoxInstructionType() == BoxInstructionType.REMOVE){
                relevantBox.removeLens(instruction.getLens());
            }
            else {
                relevantBox.addLens(instruction.getLens());
            }
        }
    }

    private void initializeBoxes() {
        for (int i = 0; i < 256; i++) {
            boxes.add(new Box(i));
        }
    }
}
