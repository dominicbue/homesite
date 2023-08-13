package domin.homesite.cookbook.recipemanagement.domain;

import java.util.ArrayList;
import java.util.List;

public class DomainTestDataFactory {

    public static final String RECIPE_ID = "Rec0";
    public static final String DUMMY_RECIPE_NAME = "Dummy Recipe";
    public static final String INSTRUCTION_DO_THIS = "Do this. Is from dummyBuilder.";
    public static final String INSTRUCTION_AND_THIS = "And this. Is from dummyBuilder.";
    public static final String INGREDIENT_APFEL = "Test Apfel";
    public static final String QUANTITY_4 = "4";
    public static final String INGREDIENT_MEHL = "Test Mehl";
    public static final String QUANTITY_400 = "400";
    public static final String DUMMY_CATEGORIE_NAME = "Dummy Category";
    public static final String INSTRUCTION_ID_INS02 = "Ins02";
    public static final String INSTRUCTION_ID_INS01 = "Ins01";

    public static Category dummyCategory() {
        return new CategoryBuilder()
                .withCategoryId("Cat01")
                .withCategoryName(DUMMY_CATEGORIE_NAME)
                .build();
    }

    public static List<Ingredient> dummyIngredient() {
        Ingredient dummy1 = new IngredientBuilder()
                .withIngredientId("Ing01")
                .withIngredientName(INGREDIENT_APFEL)
                .withQuantity(QUANTITY_4)
                .withIngredientsUnit(IngredientsUnit.STUECK)
                .build();
        Ingredient dummy2 = new IngredientBuilder()
                .withIngredientId("Ing02")
                .withIngredientName(INGREDIENT_MEHL)
                .withQuantity(QUANTITY_400)
                .withIngredientsUnit(IngredientsUnit.GRAMM)
                .build();
        List<Ingredient> ingredientEntities = new ArrayList<>();
        ingredientEntities.add(dummy1);
        ingredientEntities.add(dummy2);
        return ingredientEntities;
    }

    public static List<Instruction> dummyInstructions() {
        Instruction dummy1 = new InstructionBuilder()
                .withInstructionId(INSTRUCTION_ID_INS01)
                .withInstruction(INSTRUCTION_DO_THIS)
                .build();
        Instruction dummy2 = new InstructionBuilder()
                .withInstructionId(INSTRUCTION_ID_INS02)
                .withInstruction(INSTRUCTION_AND_THIS)
                .build();
        List<Instruction> dummyInstructions = new ArrayList<>();
        dummyInstructions.add(dummy1);
        dummyInstructions.add(dummy2);
        return dummyInstructions;
    }

    public static RecipeBuilder dummyRecipeBuilder() {
        return new RecipeBuilder()
                .withRecipeId(RECIPE_ID)
                .withRecipeName(DUMMY_RECIPE_NAME)
                .withCategory(dummyCategory())
                .withIngredients(dummyIngredient())
                .withInstructions(dummyInstructions());
    }
}
