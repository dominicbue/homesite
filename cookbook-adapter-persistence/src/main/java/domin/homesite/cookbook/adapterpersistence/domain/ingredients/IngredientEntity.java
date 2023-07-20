package domin.homesite.cookbook.adapterpersistence.domain.ingredients;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "INGREDIENTS")
@Data
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INGREDIENT_OID")
    private String object_Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "UNIT")
    private String unit;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "ingredientEntities")
    @Column(name = "RECIPE_INGREDIENT_ID")
    private List<RecipeEntity> recipe;
}
