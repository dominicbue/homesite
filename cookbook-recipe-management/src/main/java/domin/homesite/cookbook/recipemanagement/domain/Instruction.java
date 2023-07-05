package domin.homesite.cookbook.recipemanagement.domain;

import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.requireNonNull;

@Setter
@Getter
public class Instruction {
    private String instructionId;
    private String instruction;

    public Instruction(InstructionBuilder builder) {
        this.instructionId = builder.instructionId;
        this.instruction = requireNonNull(builder.instruction, "Instruction darf nicht null sein");
    }


}
