package domin.homesite.cookbook.recipemanagement.application.recipe;

import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import domin.homesite.cookbook.recipemanagement.gateway.IRecipeRepository;

import javax.inject.Inject;
import java.util.List;

public class RecipeLogic  {

    private IRecipeRepository recipeRepository ;

    @Inject
    public RecipeLogic(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.upsertRecipe(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

}
