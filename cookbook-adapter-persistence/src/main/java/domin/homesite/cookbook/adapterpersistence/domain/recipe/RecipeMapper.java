package domin.homesite.cookbook.adapterpersistence.domain.recipe;

import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryMapper;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientMapper;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionMapper;
import domin.homesite.cookbook.recipemanagement.domain.Category;
import domin.homesite.cookbook.recipemanagement.domain.Ingredient;
import domin.homesite.cookbook.recipemanagement.domain.Instruction;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;

import javax.inject.Inject;
import java.util.List;


public class RecipeMapper {

    @Inject
    private CategoryMapper categoryMapper;
    @Inject
    private IngredientMapper ingredientMapper;
    @Inject
    private InstructionMapper instructionMapper;

    public void mapDomainToEntity(Recipe recipe, RecipeEntity entity) {
        entity.setName(recipe.getRecipeName());
        entity.setObject_Id(recipe.getRecipeId());

        if (recipe.getRecipePicture() != null) {
            entity.setPicture(recipe.getRecipePicture());
        }
        if (recipe.getCategory() != null) {
            addCategoryToEntity(recipe.getCategory(), entity);
        }
        if (recipe.getIngredients().size() > 0) {
            addIngredientsToEntity(recipe.getIngredients(), entity);
        }
        if (recipe.getInstructions().size() > 0) {
            addInstructionsToEntity(recipe.getInstructions(), entity);
        }

    }

    private void addCategoryToEntity(Category category, RecipeEntity entity) {
        categoryMapper.mapDomainToEntity(category, entity.getCategoryEntity());
    }

    private void addIngredientsToEntity(List<Ingredient> ingredients, RecipeEntity entity) {
        for (Ingredient ingredient : ingredients) {
            IngredientEntity temporaryIngredientEntity = new IngredientEntity();
            ingredientMapper.mapDomainToEntity(ingredient, temporaryIngredientEntity);
            entity.getIngredientEntities().add(temporaryIngredientEntity);
        }
    }

    private void addInstructionsToEntity(List<Instruction> instructions, RecipeEntity entity) {
        for (Instruction instruction : instructions) {
            InstructionEntity temporaryInstructionEntity = new InstructionEntity();
            instructionMapper.mapDomainToEntity(instruction, temporaryInstructionEntity);
            entity.getInstructionEntities().add(temporaryInstructionEntity);
        }
    }

    public Recipe mapEntityToDomain(RecipeEntity entity, Recipe recipe) {
        return null;
    }
}
