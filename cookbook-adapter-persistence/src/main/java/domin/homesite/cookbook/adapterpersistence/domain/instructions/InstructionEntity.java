package domin.homesite.cookbook.adapterpersistence.domain.instructions;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity.*;

@ToString
@Entity
@Table(name = TABLENAME)
@Data
@NamedQueries({
        @NamedQuery(name = COUNT_INSTRUCTIONS,
                query = "SELECT count(i) FROM InstructionEntity i"),
        @NamedQuery(name = FIND_IDENTICAL_INSTRUCTION,
                query = "SELECT i FROM InstructionEntity i WHERE i.description = :" + PARAMETER_INSTRUCTION_DESCRIPTION)
}
)
public class InstructionEntity {

    public static final String TABLENAME = "INSTRUCTIONS";
    public static final String COUNT_INSTRUCTIONS = "countInstructions";
    public static final String FIND_IDENTICAL_INSTRUCTION = "findIdenticalInstruction";
    public static final String PARAMETER_INSTRUCTION_DESCRIPTION = "instructionDescription";

    @Id
    @Column(name = "INSTRUCTION_OID")
    private String instruction_id;

    @Column(name = "DESCRIPTION", nullable = false, unique = true)
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER,mappedBy = "instructionEntities")
    private Set<RecipeEntity> recipe;
}
