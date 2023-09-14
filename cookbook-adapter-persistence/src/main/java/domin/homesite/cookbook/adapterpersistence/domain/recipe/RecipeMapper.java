package domin.homesite.cookbook.adapterpersistence.domain.recipe;

import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity;
import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryMapper;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientMapper;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionMapper;
import domin.homesite.cookbook.recipemanagement.domain.*;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Log4j2
public class RecipeMapper {

    private final CategoryMapper categoryMapper;
    private final IngredientMapper ingredientMapper;
    private final InstructionMapper instructionMapper;

    @Inject
    public RecipeMapper(CategoryMapper categoryMapper, IngredientMapper ingredientMapper, InstructionMapper instructionMapper) {
        this.categoryMapper = categoryMapper;
        this.ingredientMapper = ingredientMapper;
        this.instructionMapper = instructionMapper;
    }

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
        if (recipe.getIngredients() != null) {
            addIngredientsToEntity(recipe.getIngredients(), entity);
        }
        if (recipe.getInstructions() != null) {
            addInstructionsToEntity(recipe.getInstructions(), entity);
        }

    }

    public Recipe mapEntityToDomain(RecipeEntity entity) {
        log.info("Folgende entity wird gemappt: " + entity);
        RecipeBuilder builder = new RecipeBuilder()
                .withRecipeId(entity.getRecipe_id())
                .withRecipeName(entity.getName());
        if (entity.getPicture() != null) {
            builder.withPicture(entity.getPicture());
        }
        if (entity.getCategoryEntity() != null) {
            builder.withCategory(categoryMapper.mapEntityToDomain(entity.getCategoryEntity()));
        }
        if (!entity.getIngredientEntities().isEmpty()) {
            builder.withIngredients(mapAllIngredients(entity.getIngredientEntities()));
        }
        if (!entity.getInstructionEntities().isEmpty()) {
            builder.withInstructions(mapAllInstructions(entity.getInstructionEntities()));
        }
        return builder.build();
    }

    private void addCategoryToEntity(Category category, RecipeEntity entity) {
        if (entity.getCategoryEntity() == null) {
            entity.setCategoryEntity(new CategoryEntity());
        }
        categoryMapper.mapDomainToEntity(category, entity.getCategoryEntity());
    }

    private void addIngredientsToEntity(Set<Ingredient> ingredients, RecipeEntity entity) {
        for (Ingredient ingredient : ingredients) {
            if (isIngredientAlreadyPersisted(ingredient, entity.getIngredientEntities())) {
                continue;
            }

            IngredientEntity temporaryIngredientEntity = new IngredientEntity();
            ingredientMapper.mapDomainToEntity(ingredient, temporaryIngredientEntity);
            entity.getIngredientEntities().add(temporaryIngredientEntity);
        }
    }

    private boolean isIngredientAlreadyPersisted(Ingredient ingredient, Set<IngredientEntity> persistedIngredients) {
        if(persistedIngredients == null){
            return false;
        }
        return persistedIngredients.stream().anyMatch(e -> e.getIngredient_oid().equals(ingredient.getIngredientId()));
    }

    private void addInstructionsToEntity(Set<Instruction> instructions, RecipeEntity entity) {
        for (Instruction instruction : instructions) {
            if (isInstructionAlreadyPersisted(instruction, entity.getInstructionEntities())) {
                continue;
            }
            InstructionEntity temporaryInstructionEntity = new InstructionEntity();
            instructionMapper.mapDomainToEntity(instruction, temporaryInstructionEntity);
            entity.getInstructionEntities().add(temporaryInstructionEntity);
        }
    }

    private boolean isInstructionAlreadyPersisted(Instruction instruction, Set<InstructionEntity> instructionEntities) {
        if(instructionEntities == null) {
            return false;
        }
        return instructionEntities.stream().anyMatch(i -> i.getInstruction_id().equals(instruction.getInstructionId()));
    }

    private Set<Ingredient> mapAllIngredients(Set<IngredientEntity> ingredientEntities) {
        Set<Ingredient> ingredients = new HashSet<>();
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            ingredients.add(ingredientMapper.mapEntityToDomain(ingredientEntity));
        }
        return ingredients;
    }

    private Set<Instruction> mapAllInstructions(Set<InstructionEntity> instructionEntities) {
        Set<Instruction> instructions = new HashSet<>();
        for (InstructionEntity instructionEntity : instructionEntities) {
            instructions.add(instructionMapper.mapEntityToDomain(instructionEntity));
        }
        return instructions;
    }
}
