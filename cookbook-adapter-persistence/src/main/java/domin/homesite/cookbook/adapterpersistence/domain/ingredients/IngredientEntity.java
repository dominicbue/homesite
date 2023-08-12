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
    @Column(name = "INGREDIENT_OID")
    private String ingredient_oid;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "UNIT", nullable = false)
    private String unit;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "ingredientEntities")
    @JoinColumn(name = "RECIPE_INGREDIENT_ID")
    private List<RecipeEntity> recipe;
}
