package domin.homesite.cookbook.recipemanagement.gateway.mapper;

import domin.homesite.cookbook.recipemanagement.domain.Category;
import domin.homesite.cookbook.recipemanagement.domain.CategoryBuilder;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import domin.homesite.cookbook.recipemanagement.domain.RecipeBuilder;
import domin.homesite.cookbook.recipemanagement.gateway.dto.RecipeDto;

public class RecipeMapper {

    public Recipe mapRecipeDtoToDomainRecipe(RecipeDto recipeDto) {
        Category category = new CategoryBuilder()
                .withCategoryId(recipeDto.getCategoryId())
                .withCategoryName(recipeDto.getCategoryName())
                .build();
        return new RecipeBuilder()
                .withRecipeId(recipeDto.getRecipeId())
                .withRecipeName(recipeDto.getRecipeTitle())
                .withPicture(recipeDto.getRecipePicture())
                .withCategory(category)
                .build();
    }

    public  RecipeDto mapDomainRecipeToRecipeDto(Recipe recipe) {
        RecipeDto dto = RecipeDto.builder()
                .recipeId(recipe.getRecipeId())
                .recipeTitle(recipe.getRecipeName())
                .recipePicture(recipe.getRecipePicture())
                .categoryId(recipe.getCategory().getCategoryId())
                .categoryName(recipe.getCategory().getCategoryName())
                .build();
        return dto;
    }
}
