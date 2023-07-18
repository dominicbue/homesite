package domin.homesite.cookbook.adapterpersistence.domain.instructions;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "INSTRUCTIONS")
@Data
@IdClass(InstructionEntityId.class)
public class InstructionEntity {

    @Id
    @Column(name = "OBJECT_OID")
    private String object_Id;

    @Id
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_OID", referencedColumnName = "OBJECT_OID", insertable = false, updatable = false)
    private RecipeEntity recipe;
}
