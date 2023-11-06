package domin.homesite.gil.application;

import domin.homesite.gil.adapter.recipemanagement.RecipeManagementAdapterImpl;
import domin.homesite.gil.domain.Recipe;
import domin.homesite.gil.domain.RecipeData;
import domin.homesite.gil.domain.RecipeHeader;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor(onConstructor_ = {@Inject})
public class RecipeHandler {

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
