package domin.homesite.cookbook.recipemanagement.gateway;

import domin.homesite.cookbook.recipemanagement.domain.Recipe;

import java.util.Set;

public interface IRecipeRepository {

    void upsertRecipe(Recipe recipe);

    Set<Recipe> searchRecipeByName(String recipeName);


}
