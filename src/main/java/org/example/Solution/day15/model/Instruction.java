package org.example.Solution.day15.model;

import lombok.Getter;

@Getter
public class Instruction {
    private final BoxInstructionType boxInstructionType;
    private final Lens lens;
    private final Integer targetBoxNumber;

    public Instruction(String unhashed) {
        if (unhashed.contains("-")){
            boxInstructionType = BoxInstructionType.REMOVE;
            String withoutLastChar = unhashed.substring(0, unhashed.length() - 1);
            targetBoxNumber = new ElfHasher().hashString(withoutLastChar);
            lens = new Lens(0, withoutLastChar);
        }
        else {
            boxInstructionType = BoxInstructionType.ADD;
            String label = unhashed.split("=")[0];
            targetBoxNumber = new ElfHasher().hashString(label);
            lens = new Lens(Integer.parseInt(unhashed.split("=")[1]), label);
        }
    }
}
