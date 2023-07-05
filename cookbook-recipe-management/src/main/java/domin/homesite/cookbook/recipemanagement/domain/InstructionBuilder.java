package domin.homesite.cookbook.recipemanagement.domain;

public class InstructionBuilder {

    String instructionId;
    String instruction;

    public InstructionBuilder withInstructionId(String instructionId){
        this.instructionId = instructionId;
        return this;
    }

    public InstructionBuilder withInstruction(String instruction){
        this.instruction = instruction;
        return this;
    }

    public Instruction build() { return new Instruction(this);}

}
