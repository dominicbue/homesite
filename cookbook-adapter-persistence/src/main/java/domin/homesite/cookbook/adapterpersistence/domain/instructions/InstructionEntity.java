package domin.homesite.cookbook.adapterpersistence.domain.instructions;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "INSTRUCTIONS")
@Data
public class InstructionEntity {

    @Id
    @Column(name = "INSTRUCTION_OID")
    private String object_Id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "instructionEntities")
    @Column(name = "RECIPE_INSTRUCTION_ID")
    private RecipeEntity recipe;
}
