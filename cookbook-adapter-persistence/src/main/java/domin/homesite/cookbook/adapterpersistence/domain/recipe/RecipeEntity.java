package domin.homesite.cookbook.adapterpersistence.domain.recipe;

import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity.*;

@MappedSuperclass
@Getter
@Setter
@Entity
@Table(name = TABLENAME)
@NamedQueries(
        @NamedQuery(name = SEARCH_RECIPE_WITH_NAME,
                query = "SELECT r FROM RecipeEntity r WHERE r.name = :" + PARAMETER_RECIPE_NAME)
)
public class RecipeEntity {
    public static final String TABLENAME = "RECIPES";
    public static final String SEARCH_RECIPE_WITH_NAME = "searchRecipeWithName";
    public static final String PARAMETER_RECIPE_NAME = "name";

    @Id
    @Column(name = "RECIPE_OID")
    private String recipe_id;
    @Column(name = "RECIPE_NAME", unique = true, nullable = false)
    private String name;
    @Lob
    @Column(name = "PICTURE", length = 100000)
    private byte[] picture;

    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryEntity categoryEntity;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "RECIPE_INGREDIENTS",
            joinColumns = @JoinColumn(name = "RECIPE_OID"),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENT_OID"))
    private Set<IngredientEntity> ingredientEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "RECIPE_INSTRUCTIONS",
            joinColumns = @JoinColumn(name = "RECIPE_OID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUCTION_OID"))
    private Set<InstructionEntity> instructionEntities;

}
