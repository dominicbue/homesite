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

import java.util.List;

public class RecipeMapper {

    public static final int NONE_EQUAL_ENTITY = -1;
    private final CategoryMapper categoryMapper = new CategoryMapper();
    private final IngredientMapper ingredientMapper = new IngredientMapper();
    private final InstructionMapper instructionMapper = new InstructionMapper();

    public void mapDomainToEntity(Recipe recipe, RecipeEntity entity) {
        entity.setName(recipe.getRecipeName());

        if (entity.getRecipe_id() == null) {
            entity.setRecipe_id(recipe.getRecipeId());
        }
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
            int index = this.getIndexOfExistingIngredientEntity(entity.getIngredientEntities(), ingredient.getIngredientId());
            if (index == NONE_EQUAL_ENTITY) {
                entity.getIngredientEntities().add(temporaryIngredientEntity);
            } else {
                mergeExistingEntity(entity.getIngredientEntities(), temporaryIngredientEntity, index);
            }
        }
    }

    private int getIndexOfExistingIngredientEntity(List<IngredientEntity> ingredientEntities, String ingredientId) {
        for (int i = 0; i < ingredientEntities.size(); i++) {
            if (ingredientEntities.get(i).getIngredient_oid().equals(ingredientId)) {
                return i;
            }
        }
        return NONE_EQUAL_ENTITY;
    }

    private void addInstructionsToEntity(List<Instruction> instructions, RecipeEntity entity) {
        for (Instruction instruction : instructions) {
            InstructionEntity temporaryInstructionEntity = new InstructionEntity();
            instructionMapper.mapDomainToEntity(instruction, temporaryInstructionEntity);
            int index = getIndexOfExistingInstructionEntity(entity.getInstructionEntities(), instruction.getInstructionId());
            if (index == NONE_EQUAL_ENTITY) {
                entity.getInstructionEntities().add(temporaryInstructionEntity);
            } else {
                mergeExistingEntity(entity.getInstructionEntities(), temporaryInstructionEntity, index);
            }
        }
    }

    private int getIndexOfExistingInstructionEntity(List<InstructionEntity> instructionEntities, String instructionId) {
        for (int i = 0; i < instructionEntities.size(); i++) {
            if (instructionEntities.get(i).getInstruction_id().equals(instructionId)) {
                return i;
            }
        }
        return NONE_EQUAL_ENTITY;
    }

    private <T> void mergeExistingEntity(List<T> entities, T temporaryEntity, int index) {
        entities.set(index, temporaryEntity);
    }

    public Recipe mapEntityToDomain(RecipeEntity entity) {
        return null;
    }
}
