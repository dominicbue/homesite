package domin.homesite.cookbook.adapterpersistence.domain.instructions;

import domin.homesite.cookbook.adapterpersistence.AbstractRepository;
import domin.homesite.cookbook.recipemanagement.gateway.IInstructionRepository;
import lombok.extern.log4j.Log4j2;

import javax.persistence.TypedQuery;
import java.util.Optional;

import static domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity.FIND_IDENTICAL_INSTRUCTION;
import static domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity.PARAMETER_INSTRUCTION_DESCRIPTION;
import static java.lang.String.format;


@Log4j2
public class InstructionRepositoryImpl extends AbstractRepository<InstructionEntity> implements IInstructionRepository {


    public InstructionRepositoryImpl() {}

    public Optional<InstructionEntity> findIdenticalPersistedInstruction(InstructionEntity instruction) {
        final TypedQuery<InstructionEntity> query = createNamedQuery(FIND_IDENTICAL_INSTRUCTION, InstructionEntity.class);
        query.setParameter(PARAMETER_INSTRUCTION_DESCRIPTION, instruction.getDescription());
        log.info(format("SQL-Statement von %s : %s mit 'Description : %s'",
                FIND_IDENTICAL_INSTRUCTION,
                query,
                query.getParameterValue(PARAMETER_INSTRUCTION_DESCRIPTION)));
        return Optional.of(query.getSingleResult());
    }
}
