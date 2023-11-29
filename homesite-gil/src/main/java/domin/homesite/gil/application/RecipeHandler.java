package domin.homesite.gil.application;

import domin.homesite.gil.adapter.recipemanagement.RecipeManagementAdapterImpl;
import domin.homesite.gil.domain.Recipe;
import domin.homesite.gil.domain.RecipeData;
import domin.homesite.gil.domain.RecipeHeader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class RecipeHandler {

    @Inject
    private RecipeManagementAdapterImpl recipeManagementAdapter;

    public void persistRecipeFromUi(RecipeHeader recipeHeader, RecipeData recipeData) {
        recipeManagementAdapter.saveRecipeDataFromUi(recipeHeader, recipeData);
    }

    public List<RecipeHeader> getAllRecipeHeaders() {
        return recipeManagementAdapter.getAllRecipes().stream()
                .map(Recipe::getHeader)
                .collect(Collectors.toList());
    }
}
