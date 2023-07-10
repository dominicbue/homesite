package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.recipemanagement.domain.Ingredient;
import domin.homesite.cookbook.recipemanagement.domain.Instruction;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class RecipeMapper {

    public RecipeEntity mapDomainToEntity(Recipe recipe, RecipeEntity entity) {

        entity.setName(recipe.getRecipeName());
        entity.setPicture(recipe.getRecipePicture());

        return entity;
    }

    public Recipe mapEntityToDomain(RecipeEntity entity, Recipe recipe) {
        return null;
    }
}
