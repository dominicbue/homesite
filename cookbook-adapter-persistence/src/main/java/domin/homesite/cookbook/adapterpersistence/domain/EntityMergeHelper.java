package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientMapper;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientRepositoryImpl;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionMapper;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionRepositoryImpl;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EntityMergeHelper {

    private IngredientRepositoryImpl ingredientRepository;
    private IngredientMapper ingredientMapper;
    private InstructionRepositoryImpl instructionRepository;
    private InstructionMapper instructionMapper;

    public EntityMergeHelper(IngredientRepositoryImpl ingredientRepository, InstructionRepositoryImpl instructionRepository) {
        this.ingredientRepository = ingredientRepository;
        this.instructionRepository = instructionRepository;
        this.ingredientMapper = new IngredientMapper();
        this.instructionMapper = new InstructionMapper();
    }

    public void mergeWithExistingEntities(RecipeEntity recipeEntity) {
        mergeIngredientWithExistingEntities(recipeEntity);
        mergeInstructionWithExistingEntities(recipeEntity);
    }

    private void mergeIngredientWithExistingEntities(RecipeEntity recipeEntity) {
        Set<IngredientEntity> mergedEntities = new HashSet<>();
        for (IngredientEntity entity : recipeEntity.getIngredientEntities()) {
            Optional<IngredientEntity> optionalIngredient = ingredientRepository.findIdenticalPersistedIngredient(entity);
            if (optionalIngredient.isPresent()) {
                mergedEntities.add(optionalIngredient.get());
                continue;
            }
            mergedEntities.add(entity);
        }
        recipeEntity.setIngredientEntities(mergedEntities);
    }

    private void mergeInstructionWithExistingEntities(RecipeEntity recipeEntity) {
        Set<InstructionEntity> mergedEntities = new HashSet<>();
        for (InstructionEntity entity : recipeEntity.getInstructionEntities()) {
            Optional<InstructionEntity> optionalInstruction = instructionRepository.findIdenticalPersistedInstruction(entity);
            if (optionalInstruction.isPresent()) {
                mergedEntities.add(optionalInstruction.get());
                continue;
            }
            mergedEntities.add(entity);
        }
        recipeEntity.setInstructionEntities(mergedEntities);
    }
}
