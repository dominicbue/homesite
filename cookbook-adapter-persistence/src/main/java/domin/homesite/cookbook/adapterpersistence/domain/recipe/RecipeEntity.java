package domin.homesite.cookbook.adapterpersistence.domain.recipe;

import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NamedQuery(name = "TEST", query = "SELECT r FROM RecipeEntity r")
@Table(name = "RECIPES")
public class RecipeEntity {

    @Id
    @Column(name = "RECIPE_OID")
    private String recipe_id;
    @Column(name = "RECIPE_NAME", unique = true)
    private String name;
    @Lob
    @Column(name = "PICTURE", length = 100000)
    private byte[] picture;

    @Column(name = "CATEGORY_OID")
    private String category_id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_OID", referencedColumnName = "CATEGORY_OID", insertable = false, updatable = false)
    private CategoryEntity categoryEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @Column(name = "INGREDIENT_ID")
    @JoinTable(name = "RECIPE_INGREDIENTS",
            joinColumns = @JoinColumn(name = "RECIPE_OID"),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENT_OID"))
    private List<IngredientEntity> ingredientEntities = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @Column(name = "INSTRUCTION_ID")
    @JoinTable(name = "RECIPE_INSTRUCTIONS",
            joinColumns = @JoinColumn(name = "RECIPE_OID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUCTION_OID"))
    private List<InstructionEntity> instructionEntities = new ArrayList<>();

}
