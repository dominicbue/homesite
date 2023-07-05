package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.recipemanagement.domain.Instruction;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class InstructionMapper {

    public InstructionEntity mapDomainToEntity(Instruction instruction, InstructionEntity entity) {
        entity.setObject_Id(instruction.getInstructionId());
        entity.setDescription(instruction.getInstruction());
        return entity;
    }
}
