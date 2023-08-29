package domin.homesite.cookbook.adapterpersistence.domain.instructions;

import domin.homesite.cookbook.recipemanagement.domain.Instruction;
import domin.homesite.cookbook.recipemanagement.domain.InstructionBuilder;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class InstructionMapper {

    public void mapDomainToEntity(Instruction instruction, InstructionEntity entity) {
        entity.setDescription(instruction.getInstructionText());

        if(instruction.getInstructionId() != null) {
            entity.setInstruction_id(instruction.getInstructionId());
        }
    }

    public Instruction mapEntityToDomain(InstructionEntity entity) {
        return  new InstructionBuilder()
                .withInstructionId(entity.getInstruction_id())
                .withInstruction(entity.getDescription())
                .build();
    }
}
