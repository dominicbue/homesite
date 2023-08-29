package domin.homesite.cookbook.adapterpersistence.domain.instructions;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity.TABLENAME;

@MappedSuperclass
@ToString
@Entity
@Table(name = TABLENAME)
@Data
public class InstructionEntity {

    public static final String TABLENAME = "INSTRUCTIONS";

    @Id
    @Column(name = "INSTRUCTION_OID")
    private String instruction_id;

    @Column(name = "DESCRIPTION", nullable = false, unique = true)
    private String description;

    @ManyToMany(mappedBy = "instructionEntities")
    private Set<RecipeEntity> recipe;
}
