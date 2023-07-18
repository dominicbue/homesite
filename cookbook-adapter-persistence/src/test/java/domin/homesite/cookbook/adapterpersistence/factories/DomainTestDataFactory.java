package domin.homesite.cookbook.adapterpersistence.factories;

import domin.homesite.cookbook.recipemanagement.domain.*;

import java.util.ArrayList;
import java.util.List;

public class DomainTestDataFactory {

    public static final String DOMAIN_RECIPE_ID_0 = "0";
    public static final String DOMAIN_RECIPE_NAME = "Dummy Recipe";
    public static final byte[] DOMAIN_RECIPE_PICTURE = "TEST".getBytes();
    public static final String DOMAIN_CATEGORIE_ID = "11";
    public static final String INSTRUCTION_DO_THIS = "Do this.";
    public static final String INSTRUCTION_AND_THIS = "And this.";
    public static final String INGREDIENT_APFEL = "Test Apfel";
    public static final String QUANTITY_4 = "4";
    public static final String INGREDIENT_MEHL = "Test Mehl";
    public static final String QUANTITY_400 = "400";
    public static final String DUMMY_CATEGORIE_NAME = "Dummy Categorie";

    public static Category dummyCategory() {
        return new CategoryBuilder()
                .withCategoryName(DUMMY_CATEGORIE_NAME)
                .withCategoryId(DOMAIN_CATEGORIE_ID)
                .build();
    }

    public static List<Ingredient> dummyIngredient() {
        Ingredient dummy1 = new IngredientBuilder()
                .withIngredientName(INGREDIENT_APFEL)
                .withQuantity(QUANTITY_4)
                .withIngredientsUnit(IngredientsUnit.STUECK)
                .build();
        Ingredient dummy2 = new IngredientBuilder()
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
        Instruction dummy1 = new InstructionBuilder().withInstruction(INSTRUCTION_DO_THIS).build();
        Instruction dummy2 = new InstructionBuilder().withInstruction(INSTRUCTION_AND_THIS).build();
        List<Instruction> dummyInstructions = new ArrayList<>();
        dummyInstructions.add(dummy1);
        dummyInstructions.add(dummy2);
        return dummyInstructions;
    }

    public static RecipeBuilder dummyRecipeBuilder() {
        return new RecipeBuilder()
                .withRecipeId(DOMAIN_RECIPE_ID_0)
                .withRecipeName(DOMAIN_RECIPE_NAME)
                .withPicture(DOMAIN_RECIPE_PICTURE)
                .withCategory(dummyCategory())
                .withIngredients(dummyIngredient())
                .withInstructions(dummyInstructions());
    }
}
