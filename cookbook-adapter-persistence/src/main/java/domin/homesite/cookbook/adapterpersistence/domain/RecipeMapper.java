package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.recipemanagement.domain.Ingredient;
import domin.homesite.cookbook.recipemanagement.domain.Instruction;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import java.util.ArrayList;


@AllArgsConstructor(onConstructor_ = {@Inject})
public class RecipeMapper {

    private IngredientMapper ingredientMapper;
    private InstructionMapper instructionMapper;
    private CategoryMapper categoryMapper;

    public RecipeEntity mapDomainToEntity(Recipe recipe, RecipeEntity entity) {
        entity.setObject_Id(recipe.getRecipeId());
        entity.setName(recipe.getRecipeName());
        entity.setPicture(recipe.getRecipePicture());
        entity.setInstructionEntities(new ArrayList<>());
        entity.setIngredientEntities(new ArrayList<>());

        if (recipe.getCategory() != null) {
            entity.setCategoryEntity(categoryMapper.mapDomainToEntity(recipe.getCategory()));
        }
        if (recipe.getIngredients() != null) {
            for (Ingredient currentIngredient : recipe.getIngredients()) {
                IngredientEntity temporaryEntity = new IngredientEntity();
                temporaryEntity = ingredientMapper.mapDomainToEntity(currentIngredient, temporaryEntity);
                entity.getIngredientEntities().add(temporaryEntity);
            }
        }
        if (recipe.getRecipeInstructions() != null) {
            for (Instruction currentInstruction : recipe.getRecipeInstructions()) {
                InstructionEntity temporaryEntity = new InstructionEntity();
                temporaryEntity = instructionMapper.mapDomainToEntity(currentInstruction, temporaryEntity);
                entity.getInstructionEntities().add(temporaryEntity);
            }
        }
        return entity;
    }
}
