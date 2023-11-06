package domin.homesite.gil.adapter.recipemanagement;

import domin.homesite.cookbook.recipemanagement.application.RecipeManagerFacade;
import domin.homesite.cookbook.recipemanagement.gateway.dto.RecipeDto;
import domin.homesite.gil.domain.Category;
import domin.homesite.gil.domain.Recipe;
import domin.homesite.gil.domain.RecipeData;
import domin.homesite.gil.domain.RecipeHeader;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor_ = {@Inject})
public class RecipeManagementAdapterImpl {

    private RecipeManagerFacade recipeManagerFacade;

    public void saveRecipeDataFromUi(RecipeHeader recipeHeader, RecipeData recipeData){
        RecipeDto recipeDto = RecipeDto.builder()
                .recipeId(recipeHeader.getRecipeId())
                .recipeTitle(recipeHeader.getRecipeTitle())
                .recipePicture(recipeHeader.getRecipePicture())
                .categoryId(recipeHeader.getRecipeCategory().getCategoryId())
                .categoryName(recipeHeader.getRecipeCategory().getCategoryName())
                .build();
        recipeManagerFacade.saveRecipe(recipeDto);
    }

    public List<Recipe> getAllRecipes(){
        List<RecipeDto> dtos = recipeManagerFacade.getAllRecipes();

        return dtos.stream()
                .map(this::mapToHeaderAndDataOfRecipe)
                .collect(Collectors.toList());
    }

    private Recipe mapToHeaderAndDataOfRecipe(RecipeDto dto) {
        Category category = Category.builder()
                .categoryId(dto.getCategoryId())
                .categoryName(dto.getCategoryName())
                .build();
        RecipeHeader header = RecipeHeader.builder()
                .recipeId(dto.getRecipeId())
                .recipeTitle(dto.getRecipeTitle())
                .recipePicture(dto.getRecipePicture())
                .recipeCategory(category)
                .build();
        RecipeData data = RecipeData.builder()
                .build();
        return Recipe.builder()
                .header(header)
                .data(data)
                .build();

    }

}
