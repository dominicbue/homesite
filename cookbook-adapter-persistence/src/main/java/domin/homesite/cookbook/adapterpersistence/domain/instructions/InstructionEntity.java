package domin.homesite.cookbook.adapterpersistence.domain.instructions;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "INSTRUCTIONS")
@Data
public class InstructionEntity {

    @Id
    @Column(name = "INSTRUCTION_OID")
    private String instruction_id;

    @Column(name = "DESCRIPTION", nullable = false, unique = true)
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "instructionEntities")
    @JoinColumn(name = "RECIPE_INSTRUCTION_ID")
    private List<RecipeEntity> recipe;
}
