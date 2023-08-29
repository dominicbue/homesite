package domin.homesite.cookbook.adapterpersistence.domain.ingredients;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity.TABLENAME;

@MappedSuperclass
@ToString
@Entity
@Table(name = TABLENAME)
@Data
public class IngredientEntity {

    public static final String TABLENAME = "INGREDIENTS";

    @Id
    @Column(name = "INGREDIENT_OID")
    private String ingredient_oid;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "UNIT", nullable = false)
    private String unit;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ingredientEntities")
    private Set<RecipeEntity> recipe;
}
