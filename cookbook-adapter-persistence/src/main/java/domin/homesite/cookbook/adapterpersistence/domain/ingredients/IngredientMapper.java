package domin.homesite.cookbook.adapterpersistence.domain.ingredients;

import domin.homesite.cookbook.recipemanagement.domain.Ingredient;
import domin.homesite.cookbook.recipemanagement.domain.IngredientBuilder;
import domin.homesite.cookbook.recipemanagement.domain.IngredientsUnit;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IngredientMapper {

    public void mapDomainToEntity(Ingredient ingredient, IngredientEntity entity) {
        entity.setName(ingredient.getIngredientName());
        entity.setUnit(ingredient.getUnit().value);

        if(ingredient.getQuantity() != null) {
            entity.setQuantity(ingredient.getQuantity());
        }
        if(ingredient.getIngredientId() != null){
            entity.setIngredient_oid(ingredient.getIngredientId());
        }
    }

    public Ingredient mapEntityToDomain(IngredientEntity entity) {
        return new IngredientBuilder()
                .withIngredientId(entity.getIngredient_oid())
                .withIngredientName(entity.getName())
                .withQuantity(entity.getQuantity())
                .withIngredientsUnit(IngredientsUnit.fromValue(entity.getUnit()))
                .build();

    }
}

