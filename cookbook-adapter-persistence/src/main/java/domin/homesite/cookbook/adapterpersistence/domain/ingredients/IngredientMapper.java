package domin.homesite.cookbook.adapterpersistence.domain.ingredients;

import domin.homesite.cookbook.recipemanagement.domain.Ingredient;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class IngredientMapper {

    public void mapDomainToEntity(Ingredient ingredient, IngredientEntity entity) {
        entity.setName(ingredient.getIngredientName());
        entity.setUnit(ingredient.getUnit().toString());

        if(ingredient.getQuantity() != null) {
            entity.setQuantity(ingredient.getQuantity());
        }
        if(ingredient.getIngredientId() != null){
            entity.setIngredient_oid(ingredient.getIngredientId());
        }
    }
}

