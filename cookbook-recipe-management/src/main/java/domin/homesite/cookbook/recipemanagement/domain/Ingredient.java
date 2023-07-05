package domin.homesite.cookbook.recipemanagement.domain;

import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.requireNonNull;

@Setter
@Getter
public class Ingredient {
    private String ingredientId;
    private String ingredientName;
    private String quantity;
    private IngredientsUnit unit;

    public Ingredient (IngredientBuilder builder){
        this.ingredientId = builder.ingredientId;
        this.ingredientName = requireNonNull(builder.ingredientName, "IngrdientName darf nicht null sein");
        this.quantity = requireNonNull(builder.quantity, "Quantity darf nicht null sein");
        this.unit = requireNonNull(builder.unit, "Unit darf nicht null sein");
    }



}
