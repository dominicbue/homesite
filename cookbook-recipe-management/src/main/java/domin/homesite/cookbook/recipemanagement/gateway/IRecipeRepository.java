package domin.homesite.cookbook.recipemanagement.gateway;

import domin.homesite.cookbook.recipemanagement.domain.Recipe;

import java.util.List;

public interface IRecipeRepository {

    void upsertRecipe(Recipe recipe);

    List<Recipe> readAllRecipes();


}
