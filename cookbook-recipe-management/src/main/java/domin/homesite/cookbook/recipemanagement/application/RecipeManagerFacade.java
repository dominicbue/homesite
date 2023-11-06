package domin.homesite.cookbook.recipemanagement.application;

import domin.homesite.cookbook.recipemanagement.application.recipe.RecipeLogic;
import domin.homesite.cookbook.recipemanagement.gateway.dto.RecipeDto;
import domin.homesite.cookbook.recipemanagement.gateway.mapper.RecipeMapper;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor_ = {@Inject})
public class RecipeManagerFacade {

    private final RecipeLogic recipeLogic;
    private final RecipeMapper recipeMapper;

    public void saveRecipe(RecipeDto recipeDto) {
        recipeLogic.saveRecipe(recipeMapper.mapRecipeDtoToDomainRecipe(recipeDto));
    }
    public List<RecipeDto> getAllRecipes() {

        return recipeLogic.getAllRecipes()
                .stream()
                .map(recipeMapper::mapDomainRecipeToRecipeDto)
                .collect(Collectors.toList());
    }
}
