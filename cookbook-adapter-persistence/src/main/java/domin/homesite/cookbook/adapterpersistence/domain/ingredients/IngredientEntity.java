package domin.homesite.cookbook.adapterpersistence.domain.ingredients;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity.*;

@ToString
@Entity
@Table(name = TABLENAME)
@Data
@NamedQueries({
        @NamedQuery(name = COUNT_INGREDIENTS,
                query = "SELECT count(i) FROM IngredientEntity i"),
        @NamedQuery(name = FIND_IDENTICAL_INGREDIENT,
                query = "SELECT i FROM IngredientEntity i WHERE i.name = :" + PARAMETER_INGREDIENT_NAME + " AND i.quantity = :" + PARAMETER_INGREDIENT_QUANTITY + " AND i.unit = :" + PARAMETER_INGREDIENT_UNIT)
})
public class IngredientEntity {

    public static final String TABLENAME = "INGREDIENTS";
    public static final String COUNT_INGREDIENTS= "countIngredients";
    public static final String FIND_IDENTICAL_INGREDIENT = "findIdenticalIngredient";
    public static final String PARAMETER_INGREDIENT_NAME = "ingredientName";
    public static final String PARAMETER_INGREDIENT_QUANTITY = "ingredientQuantity";
    public static final String PARAMETER_INGREDIENT_UNIT = "ingredientUnit";

    @Id
    @Column(name = "INGREDIENT_OID")
    private String ingredient_oid;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "UNIT", nullable = false)
    private String unit;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "ingredientEntities")
    private Set<RecipeEntity> recipe;
}
