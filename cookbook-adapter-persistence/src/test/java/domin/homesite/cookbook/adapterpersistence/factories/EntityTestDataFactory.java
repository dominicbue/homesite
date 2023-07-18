package domin.homesite.cookbook.adapterpersistence.factories;

import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;

import java.util.ArrayList;
import java.util.List;

public class EntityTestDataFactory {

    public static final String DUMMY_RECIPE_ID = "0";
    public static final byte[] DUMMY_PICTURE = "Test".getBytes();
    public static final String DUMMY_RECIPE_NAME = "Dummy Recipe";
    public static final String INSTRUCTION_DO_THIS = "Do this.";
    public static final String INSTRUCTION_AND_THIS = "And this.";
    public static final String INGREDIENT_APFEL = "Test Apfel";
    public static final String QUANTITY_4 = "4";
    public static final String INGREDIENT_MEHL = "Test Mehl";
    public static final String QUANTITY_400 = "400";
    public static final String DUMMY_CATEGORIE_NAME = "Dummy Categorie";
    public static final String INGRDIENT_ENTITY_UNIT_STK = "Stk.";
    public static final String INGREDIENT_ENTITY_UNIT_G = "g";

    public static CategoryEntity dummyCategoryEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(DUMMY_CATEGORIE_NAME);
        return categoryEntity;
    }

    public static List<IngredientEntity> dummyIngredientEntities() {
        IngredientEntity dummy1 = new IngredientEntity();
        dummy1.setName(INGREDIENT_APFEL);
        dummy1.setQuantity(QUANTITY_4);
        dummy1.setUnit(INGRDIENT_ENTITY_UNIT_STK);
        IngredientEntity dummy2 = new IngredientEntity();
        dummy2.setName(INGREDIENT_MEHL);
        dummy2.setQuantity(QUANTITY_400);
        dummy2.setQuantity(INGREDIENT_ENTITY_UNIT_G);
        List<IngredientEntity> ingredientEntities = new ArrayList<>();
        ingredientEntities.add(dummy1);
        ingredientEntities.add(dummy2);
        return ingredientEntities;
    }

    public static List<InstructionEntity> dummyInstructionEntities() {
        InstructionEntity dummy1 = new InstructionEntity();
        dummy1.setDescription(INSTRUCTION_DO_THIS);
        InstructionEntity dummy2 = new InstructionEntity();
        dummy2.setDescription(INSTRUCTION_AND_THIS);
        List<InstructionEntity> dummyInstructionEntities = new ArrayList<>();
        dummyInstructionEntities.add(dummy1);
        dummyInstructionEntities.add(dummy2);
        return dummyInstructionEntities;
    }

    public static RecipeEntity dummyRecipeEnitity() {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setObject_Id(DUMMY_RECIPE_ID);
        recipeEntity.setName(DUMMY_RECIPE_NAME);
        recipeEntity.setPicture(DUMMY_PICTURE);
        recipeEntity.setCategoryEntity(dummyCategoryEntity());
        recipeEntity.setIngredientEntities(dummyIngredientEntities());
        recipeEntity.setInstructionEntities(dummyInstructionEntities());
        return recipeEntity;
    }
}
