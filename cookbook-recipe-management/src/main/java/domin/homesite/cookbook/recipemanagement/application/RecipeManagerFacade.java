package domin.homesite.cookbook.recipemanagement.application;

import domin.homesite.cookbook.recipemanagement.application.recipe.RecipeLogic;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.Set;

@Log4j2

public class RecipeManagerFacade {
    private final RecipeLogic recipeLogic;

    @Inject
    public RecipeManagerFacade(RecipeLogic recipeLogic) {
        this.recipeLogic = recipeLogic;
    }

    public void saveRecipe(Recipe recipe) {
        recipeLogic.saveRecipe(recipe);
    }
    public Set<Recipe> searchRecipeWithPartOfName(String textToLookFor) {
        recipeLogic.searchRecipeWithPartOfName(textToLookFor);
    }
}
