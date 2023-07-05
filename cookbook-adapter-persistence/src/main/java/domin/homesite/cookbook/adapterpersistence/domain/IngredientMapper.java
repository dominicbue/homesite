package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.recipemanagement.domain.Ingredient;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class IngredientMapper {

    public IngredientEntity mapDomainToEntity(Ingredient ingredient, IngredientEntity entity) {
        entity.setObject_Id(ingredient.getIngredientId());
        entity.setName(ingredient.getIngredientName());
        entity.setQuantity(ingredient.getQuantity());
        entity.setUnit(ingredient.getUnit().toString());
        return entity;
    }
}

