package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.adapterpersistence.AbstractRepositoryTest;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientRepositoryImpl;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionRepositoryImpl;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeMapper;
import domin.homesite.cookbook.recipemanagement.domain.*;
import org.dbunit.DatabaseUnitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity.COUNT_CATEGORIES;
import static domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity.COUNT_INGREDIENTS;
import static domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity.COUNT_INSTRUCTIONS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RecipeRepositoryImplTest extends AbstractRepositoryTest {

    public static final String RECIPE_NAME_INITIAL_DB_UNIT = "RecipeName initial dbUnit";
    public static final String RECIPE_ID_INITIAL_DB_UNIT = "dbUnitRec01";
    public static final String INITIAL_DBUNIT_XML = "dbunit_recipes_initial_data.xml";
    public static final String CATEGORY_ID_INITIAL_DB_UNIT = "dbUnitCat1";
    public static final String CATEGORY_NAME_INITIAL_DB_UNIT = "Category aus dbUnit";
    public static final String RECIPE_ID_EXTRA = "new";
    public static final String RECIPE_NAME_EXTRA = "new Recipe";
    public static final String INSTRUCTION_ID_INITIAL_DB_UNIT = "Ins01";
    public static final String INSTRUCTION_TEXT_INITIAL_DB_UNIT = "Do this for dbUnit.";
    public static final IngredientsUnit INGREDIENTS_UNIT_INITIAL_DB_UNIT = IngredientsUnit.STUECK;
    public static final String INGREDIENT_ID_INITIAL_DB_UNIT = "Ing01";
    public static final String INGREDIENT_NAME_INITIAL_DB_UNIT = "Test Apfel";
    public static final String INGREDIENT_QUANTITY_INITIAL_DB_UNIT = "4";
    public static final String INGREDIENT_ID_EXTRA_INGEXTRA = "IngExtra";
    public static final String INGREDIENT_QUANTITY_EXTRA_400 = "400";
    public static final IngredientsUnit INGREDIENTS_UNIT_EXTRA_GRAMM = IngredientsUnit.GRAMM;
    private static final String INGREDIENT_NAME_EXTRA_MEHL = "Test Mehl";
    private static final String INSTRUCTION_ID_EXTRA = "InsExtra";
    private static final String INSTRUCTION_TEXT_EXTRA = "Do this Extra";
    private RecipeRepositoryImpl testee;

    @BeforeEach
    void setup() {
        IngredientRepositoryImpl ingredientRepository = new IngredientRepositoryImpl();
        injectEntityManagerToClass(ingredientRepository);
        InstructionRepositoryImpl instructionRepository = new InstructionRepositoryImpl();
        injectEntityManagerToClass(instructionRepository);
        EntityMergeHelper mergeHelper = new EntityMergeHelper(ingredientRepository, instructionRepository);

        RecipeMapper recipeMapper = new RecipeMapper();
        testee = new RecipeRepositoryImpl(recipeMapper, mergeHelper);
        injectEntityManagerToClass(testee);

    }

    @Test
    void upsertRecipe_when_newRecipeWithNewNameAndSameCategory_then_noExceptionThrown() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile(INITIAL_DBUNIT_XML);
        Category category = new CategoryBuilder()
                .withCategoryId(CATEGORY_ID_INITIAL_DB_UNIT)
                .withCategoryName(CATEGORY_NAME_INITIAL_DB_UNIT)
                .build();

        Recipe expectedRecipe = new RecipeBuilder()
                .withRecipeId(RECIPE_ID_EXTRA)
                .withRecipeName(RECIPE_NAME_EXTRA)
                .withCategory(category)
                .build();

        //act
        transactionBegin();
        testee.upsertRecipe(expectedRecipe);
        transactionCommit();

        //assert
        RecipeEntity dbUnitInitialized = em.find(RecipeEntity.class, RECIPE_ID_INITIAL_DB_UNIT);
        RecipeEntity upserted = em.find(RecipeEntity.class, RECIPE_ID_EXTRA);

        assertEquals(CATEGORY_ID_INITIAL_DB_UNIT, dbUnitInitialized.getCategoryEntity().getCategory_oid());
        assertEquals(RECIPE_NAME_INITIAL_DB_UNIT, dbUnitInitialized.getName());
        assertEquals(CATEGORY_ID_INITIAL_DB_UNIT, upserted.getCategoryEntity().getCategory_oid());
        assertEquals(RECIPE_NAME_EXTRA, upserted.getName());

        Query categoryCountQuery = em.createNamedQuery(COUNT_CATEGORIES);
        int persistedCategories = categoryCountQuery.getSingleResult().hashCode();
        assertEquals(1, persistedCategories);
    }

    @Test
    void upsertRecipe_when_newRecipeWithNewNameAndSameIngredientAndInstruction_then_noExceptionThrown() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile(INITIAL_DBUNIT_XML);
        Ingredient ingredientInitial = new IngredientBuilder()
                .withIngredientId(INGREDIENT_ID_INITIAL_DB_UNIT)
                .withIngredientName(INGREDIENT_NAME_INITIAL_DB_UNIT)
                .withQuantity(INGREDIENT_QUANTITY_INITIAL_DB_UNIT)
                .withIngredientsUnit(INGREDIENTS_UNIT_INITIAL_DB_UNIT)
                .build();
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredientInitial);
        Instruction instructionInitial = new InstructionBuilder()
                .withInstruction(INSTRUCTION_TEXT_INITIAL_DB_UNIT)
                .withInstructionId(INSTRUCTION_ID_INITIAL_DB_UNIT)
                .build();
        Set<Instruction> instructions = new HashSet<>();
        instructions.add(instructionInitial);
        Category category = new CategoryBuilder()
                .withCategoryId(CATEGORY_ID_INITIAL_DB_UNIT)
                .withCategoryName(CATEGORY_NAME_INITIAL_DB_UNIT)
                .build();

        Recipe expectedRecipe = new RecipeBuilder()
                .withRecipeId(RECIPE_ID_EXTRA)
                .withRecipeName(RECIPE_NAME_EXTRA)
                .withCategory(category)
                .withIngredients(ingredients)
                .withInstructions(instructions)
                .build();

        //act
        transactionBegin();
        testee.upsertRecipe(expectedRecipe);
        transactionCommit();

        //assert
        RecipeEntity dbUnitInitialized = em.find(RecipeEntity.class, RECIPE_ID_INITIAL_DB_UNIT);
        RecipeEntity upserted = em.find(RecipeEntity.class, RECIPE_ID_EXTRA);

        assertEquals(CATEGORY_ID_INITIAL_DB_UNIT, dbUnitInitialized.getCategoryEntity().getCategory_oid());
        assertEquals(RECIPE_NAME_INITIAL_DB_UNIT, dbUnitInitialized.getName());
        assertEquals(CATEGORY_ID_INITIAL_DB_UNIT, upserted.getCategoryEntity().getCategory_oid());
        assertEquals(RECIPE_NAME_EXTRA, upserted.getName());

        Query ingredientCountQuery = em.createNamedQuery(COUNT_INGREDIENTS);
        int persistedIngredients = ingredientCountQuery.getSingleResult().hashCode();
        assertEquals(1, persistedIngredients);

        Query instructionCountQuery = em.createNamedQuery(COUNT_INSTRUCTIONS);
        int persistedInstructions = instructionCountQuery.getSingleResult().hashCode();
        assertEquals(1, persistedInstructions);
    }

    @Test
    void upsertRecipe_when_recipeWithSameIdButOtherName_then_upsertRecipeWithIdOfDbAndDataFromRecipe() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile(INITIAL_DBUNIT_XML);
        Category category = new CategoryBuilder()
                .withCategoryId(CATEGORY_ID_INITIAL_DB_UNIT)
                .withCategoryName(CATEGORY_NAME_INITIAL_DB_UNIT)
                .build();

        Recipe expectedRecipe = new RecipeBuilder()
                .withRecipeId(RECIPE_ID_INITIAL_DB_UNIT)
                .withRecipeName(RECIPE_NAME_EXTRA)
                .withCategory(category)
                .build();

        //act
        transactionBegin();
        testee.upsertRecipe(expectedRecipe);
        transactionCommit();

        //assert
        transactionBegin();
        RecipeEntity upsertedEntity = em.find(RecipeEntity.class, RECIPE_ID_INITIAL_DB_UNIT);
        transactionCommit();

        assertEquals(RECIPE_ID_INITIAL_DB_UNIT, upsertedEntity.getRecipe_id());
        assertEquals(RECIPE_NAME_EXTRA, expectedRecipe.getRecipeName());
        assertEquals(CATEGORY_NAME_INITIAL_DB_UNIT, upsertedEntity.getCategoryEntity().getName());
        assertEquals(1, upsertedEntity.getInstructionEntities().size());
        assertTrue(upsertedEntity.getInstructionEntities().stream().anyMatch(e -> e.getInstruction_id().equals(INSTRUCTION_ID_INITIAL_DB_UNIT)));
        assertTrue(upsertedEntity.getInstructionEntities().stream().anyMatch(e -> e.getDescription().equals(INSTRUCTION_TEXT_INITIAL_DB_UNIT)));
        assertEquals(1, upsertedEntity.getIngredientEntities().size());
        assertTrue(upsertedEntity.getIngredientEntities().stream().anyMatch(e -> e.getName().equals(INGREDIENT_NAME_INITIAL_DB_UNIT)));
        assertTrue(upsertedEntity.getIngredientEntities().stream().anyMatch(e -> e.getIngredient_oid().equals(INGREDIENT_ID_INITIAL_DB_UNIT)));
    }

    @Test
    void upsertRecipe_when_recipeIdenticButWithExtraIngredient_then_addNewIngredient() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile(INITIAL_DBUNIT_XML);
        Ingredient ingredientInitial = new IngredientBuilder()
                .withIngredientId(INGREDIENT_ID_INITIAL_DB_UNIT)
                .withIngredientName(INGREDIENT_NAME_INITIAL_DB_UNIT)
                .withQuantity(INGREDIENT_QUANTITY_INITIAL_DB_UNIT)
                .withIngredientsUnit(INGREDIENTS_UNIT_INITIAL_DB_UNIT)
                .build();
        Ingredient ingredientExtra = new IngredientBuilder()
                .withIngredientId(INGREDIENT_ID_EXTRA_INGEXTRA)
                .withIngredientName(INGREDIENT_NAME_EXTRA_MEHL)
                .withQuantity(INGREDIENT_QUANTITY_EXTRA_400)
                .withIngredientsUnit(INGREDIENTS_UNIT_EXTRA_GRAMM)
                .build();
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredientInitial);
        ingredients.add(ingredientExtra);

        Recipe expectedRecipe = new RecipeBuilder()
                .withRecipeId(RECIPE_ID_INITIAL_DB_UNIT)
                .withRecipeName(RECIPE_NAME_INITIAL_DB_UNIT)
                .withIngredients(ingredients)
                .build();

        //act
        transactionBegin();
        testee.upsertRecipe(expectedRecipe);
        transactionCommit();

        //assert
        RecipeEntity upsertedRecipe = em.find(RecipeEntity.class, RECIPE_ID_INITIAL_DB_UNIT);
        assertEquals(RECIPE_NAME_INITIAL_DB_UNIT, upsertedRecipe.getName());
        assertEquals(CATEGORY_ID_INITIAL_DB_UNIT, upsertedRecipe.getCategoryEntity().getCategory_oid());
        assertTrue(upsertedRecipe.getIngredientEntities().stream().anyMatch(i -> i.getIngredient_oid().equals(INGREDIENT_ID_INITIAL_DB_UNIT)));
        assertTrue(upsertedRecipe.getIngredientEntities().stream().anyMatch(i -> i.getIngredient_oid().equals(INGREDIENT_ID_EXTRA_INGEXTRA)));
        assertTrue(upsertedRecipe.getInstructionEntities().stream().anyMatch(i -> i.getInstruction_id().equals(INSTRUCTION_ID_INITIAL_DB_UNIT)));

    }

    @Test
    void upsertRecipe_when_recipeIdenticButWithExtraInstruction_then_addNewInstruction() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile(INITIAL_DBUNIT_XML);
        Instruction instructionInitial = new InstructionBuilder()
                .withInstruction(INSTRUCTION_TEXT_INITIAL_DB_UNIT)
                .withInstructionId(INSTRUCTION_ID_INITIAL_DB_UNIT)
                .build();
        Instruction instructionExtra = new InstructionBuilder()
                .withInstruction(INSTRUCTION_TEXT_EXTRA)
                .withInstructionId(INSTRUCTION_ID_EXTRA)
                .build();
        Set<Instruction> instructions = new HashSet<>();
        instructions.add(instructionInitial);
        instructions.add(instructionExtra);
        Recipe expectedRecipe = new RecipeBuilder()
                .withRecipeId(RECIPE_ID_INITIAL_DB_UNIT)
                .withRecipeName(RECIPE_NAME_INITIAL_DB_UNIT)
                .withInstructions(instructions)
                .build();

        //act
        transactionBegin();
        testee.upsertRecipe(expectedRecipe);
        transactionCommit();

        //assert
        RecipeEntity upsertedRecipe = em.find(RecipeEntity.class, RECIPE_ID_INITIAL_DB_UNIT);
        assertEquals(RECIPE_NAME_INITIAL_DB_UNIT, upsertedRecipe.getName());
        assertEquals(CATEGORY_ID_INITIAL_DB_UNIT, upsertedRecipe.getCategoryEntity().getCategory_oid());
        assertTrue(upsertedRecipe.getIngredientEntities().stream().anyMatch(i -> i.getIngredient_oid().equals(INGREDIENT_ID_INITIAL_DB_UNIT)));
        assertTrue(upsertedRecipe.getInstructionEntities().stream().anyMatch(i -> i.getInstruction_id().equals(INSTRUCTION_ID_INITIAL_DB_UNIT)));
        assertTrue(upsertedRecipe.getInstructionEntities().stream().anyMatch(i -> i.getInstruction_id().equals(INSTRUCTION_ID_EXTRA)));
    }

    @Test
    void searchRecipeByName_when_noMatchingRecipe_then_returnEmptyList() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile(INITIAL_DBUNIT_XML);
        List<Recipe> expectedRecipes;

        //act
        transactionBegin();
        expectedRecipes = testee.searchRecipeByName(RECIPE_NAME_EXTRA);
        transactionCommit();

        //assert
        assertTrue(expectedRecipes.isEmpty());
    }

    @Test
    void searchRecipeByName_when_matchingRecipe_then_returnRecipe() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile(INITIAL_DBUNIT_XML);
        List<Recipe> expectedRecipes;

        //act
        transactionBegin();
        expectedRecipes = testee.searchRecipeByName(RECIPE_NAME_INITIAL_DB_UNIT);
        transactionCommit();

        //assert
        assertEquals(1, expectedRecipes.size());
        Recipe recipe = expectedRecipes.get(0);

        assertEquals(RECIPE_NAME_INITIAL_DB_UNIT, recipe.getRecipeName());
        assertEquals(RECIPE_ID_INITIAL_DB_UNIT, recipe.getRecipeId());
        Set<Ingredient> expectedIngredients = recipe.getIngredients();
        Set<Instruction> expectedInstructions = recipe.getInstructions();
        assertEquals(1, expectedInstructions.size());
        assertEquals(1, expectedIngredients.size());
        Ingredient ingredient = expectedIngredients.iterator().next();
        Instruction instruction = expectedInstructions.iterator().next();
        assertEquals(INGREDIENT_NAME_INITIAL_DB_UNIT, ingredient.getIngredientName());
        assertEquals("4", ingredient.getQuantity());
        assertEquals(IngredientsUnit.STUECK, ingredient.getUnit());
        assertEquals(INSTRUCTION_TEXT_INITIAL_DB_UNIT, instruction.getInstructionText());
    }
}