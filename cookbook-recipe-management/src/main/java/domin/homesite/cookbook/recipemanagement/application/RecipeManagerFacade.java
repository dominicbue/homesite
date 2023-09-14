package domin.homesite.cookbook.recipemanagement.application;

import domin.homesite.cookbook.recipemanagement.application.recipe.RecipeLogic;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import java.util.Set;

@AllArgsConstructor(onConstructor_ = {@Inject})
public class RecipeManagerFacade {

    private final RecipeLogic recipeLogic;

    public void saveRecipe(Recipe recipe) {
        recipeLogic.saveRecipe(recipe);
    }
    public Set<Recipe> searchRecipeWithPartOfName(String textToLookFor) {
        //ToDo: SearchLogic implementation
        recipeLogic.searchRecipeWithPartOfName(textToLookFor);
        return null;
    }
}
