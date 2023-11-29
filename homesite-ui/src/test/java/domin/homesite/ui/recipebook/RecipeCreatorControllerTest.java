package domin.homesite.ui.recipebook;

import domin.homesite.gil.application.RecipeHandler;
import domin.homesite.gil.domain.Category;
import domin.homesite.gil.domain.IngredientsUnit;
import domin.homesite.gil.domain.RecipeHeader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeCreatorControllerTest {

    public static final String TEST_CATEGORY_ID = "testCat";
    public static final String TEST_CATEGORY_NAME = "Dies ist ein Test";
    public static final String INGREDIENT_NAME_MEHL = "Mehl";
    public static final String INGREDIENT_UNIT_GRAMM = IngredientsUnit.GRAMM.value;
    public static final String QUANTITY_400 = "400";
    public static final String INSTRUCTION_TEST = "Der Test wird es zeigen.";


    @Mock
    private RecipeHandler recipeHandler;
    @InjectMocks
    private RecipeCreatorController testee;

    @Test
    public void getRecipeCategory_when_recipeCategoryExist_then_returnCategory() {
        //arrange
        Category expected = Category.builder()
                .categoryId(TEST_CATEGORY_ID)
                .categoryName(TEST_CATEGORY_NAME)
                .build();
        testee.setRecipeCategory(expected);

        //act
        Category result = testee.getRecipeCategory();

        //assert
        assertNotNull(result);
        assertEquals(TEST_CATEGORY_ID, result.getCategoryId());
        assertEquals(TEST_CATEGORY_NAME, result.getCategoryName());
    }

    @Test
    public void setNewCategoryName_when_inputStringIsNull_then_newCategoryNameIsNull() {
        //arrange
        Category category = Category.builder()
                .categoryId("cat01")
                .categoryName("KategorieTest")
                .build();
        testee.setPersistedCategories(Collections.singletonList(category));

        //act
        testee.setNewCategoryName(null);

        //assert
        assertNull(testee.getNewCategoryName());
    }

    @Test
    public void setNewCategoryName_when_inputStringWithNameNotDuplicate_then_newNameIsSetAndDuplicateFalse() {
        //arrange
        Category fruehstueck = Category.builder().categoryId("cat01").categoryName("Frühstück").build();
        Category dessert = Category.builder().categoryId("cat02").categoryName("Dessert").build();
        List<Category> categoriesInDB = Arrays.asList(fruehstueck, dessert);
        testee.setPersistedCategories(categoriesInDB);
        String newInputName = "newCategory";

        //act
        testee.setNewCategoryName(newInputName);

        //assert
        assertNotNull(testee.getNewCategoryName());
        assertEquals(newInputName, testee.getNewCategoryName());
        assertFalse(testee.getIsCategoryDuplicate());
    }

    @Test
    public void setNewCategoryName_when_inputStringWithNameDuplicate_then_duplicateTrueAndNewNameIsNull() {
        //arrange
        Category fruehstueck = Category.builder().categoryId("cat01").categoryName("Frühstück").build();
        Category dessert = Category.builder().categoryId("cat02").categoryName("Dessert").build();
        List<Category> categoriesInDB = Arrays.asList(fruehstueck, dessert);
        testee.setPersistedCategories(categoriesInDB);
        String newInputName = "Frühstück";

        //act
        testee.setNewCategoryName(newInputName);

        //assert
        assertNull(testee.getNewCategoryName());
        assertTrue(testee.getIsCategoryDuplicate());
    }

    @Test
    public void addIngredient_when_onlyNewIngredientQuantityIsNull_then_returnNull() {
        //arrange
        testee.setNewIngredientName(INGREDIENT_NAME_MEHL);
        testee.setSelectedUnit(INGREDIENT_UNIT_GRAMM);

        //act
        String result = testee.addIngredient();

        //assert
        assertEquals(INGREDIENT_NAME_MEHL, testee.getNewIngredientName());
        assertEquals(INGREDIENT_UNIT_GRAMM, testee.getSelectedUnit());
        assertTrue(testee.getRecipeIngredients().isEmpty());
        assertNull(result);
    }

    @Test
    public void addIngredient_when_onlyNewIngredientNameIsNull_then_returnNull() {
        //arrange
        testee.setSelectedUnit(INGREDIENT_UNIT_GRAMM);
        testee.setNewIngredientQuantity(QUANTITY_400);

        //act
        String result = testee.addIngredient();

        //assert
        assertEquals(QUANTITY_400, testee.getNewIngredientQuantity());
        assertEquals(INGREDIENT_UNIT_GRAMM, testee.getSelectedUnit());
        assertTrue(testee.getRecipeIngredients().isEmpty());
        assertNull(result);
    }

    @Test
    public void addIngredient_when_onlySelectedUnitIsNull_then_returnNull() {
        //arrange
        testee.setNewIngredientName(INGREDIENT_NAME_MEHL);
        testee.setNewIngredientQuantity(QUANTITY_400);

        //act
        String result = testee.addIngredient();

        //assert
        assertEquals(INGREDIENT_NAME_MEHL, testee.getNewIngredientName());
        assertEquals(QUANTITY_400, testee.getNewIngredientQuantity());
        assertTrue(testee.getRecipeIngredients().isEmpty());
        assertNull(result);
    }

    @Test
    public void addIngredient_when_inputComplete_then_addIngredientToRecipeIngredients() {
        //arrange
        testee.setNewIngredientName(INGREDIENT_NAME_MEHL);
        testee.setNewIngredientQuantity(QUANTITY_400);
        testee.setSelectedUnit(INGREDIENT_UNIT_GRAMM);

        //act
        String result = testee.addIngredient();

        //assert
        assertEquals(INGREDIENT_NAME_MEHL, testee.getNewIngredientName());
        assertEquals(QUANTITY_400, testee.getNewIngredientQuantity());
        assertEquals(INGREDIENT_UNIT_GRAMM, testee.getSelectedUnit());
        assertEquals(1, testee.getRecipeIngredients().size());
        assertNull(result);
    }

    @Test
    public void addInstruction_when_newInstructionIsNull_then_returnNull() {
        //arrange

        //act
        String result = testee.addInstruction();

        //assert
        assertNull(testee.getNewInstruction());
        assertTrue(testee.getRecipeInstructions().isEmpty());
        assertNull(result);
    }

    @Test
    public void addInstruction_when_newInstructionIsSet_then_addInstructionToRecipeInstructionsAndReturnNull() {
        //arrange
        testee.setNewInstruction(INSTRUCTION_TEST);

        //act
        String result = testee.addInstruction();

        //assert
        assertEquals(INSTRUCTION_TEST, testee.getNewInstruction());
        assertEquals(1, testee.getRecipeInstructions().size());
        assertNull(result);
    }

    @Test
    public void getPersistedCategories_when_noPersistedCategoriesInDB_then_returnEmptyListl() {
        //arrange
        when(recipeHandler.getAllRecipeHeaders()).thenReturn(null);

        //act
        List<Category> result = testee.getPersistedCategories();

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void getPersistedCategories_when_oneCategoryIsPersisted_then_returnCategory() {
        //arrange
        Category category = Category.builder()
                .categoryId(TEST_CATEGORY_ID)
                .categoryName(TEST_CATEGORY_NAME)
                .build();
        RecipeHeader recipeHeader = RecipeHeader.builder()
                .recipeId("rec01")
                .recipeTitle("RecipeTest")
                .recipePicture(null)
                .recipeCategory(category)
                .build();
        when(recipeHandler.getAllRecipeHeaders()).thenReturn(Collections.singletonList(recipeHeader));

        //act
        List<Category> result = testee.getPersistedCategories();

        //assert
        assertEquals(1, result.size());
        assertEquals(TEST_CATEGORY_ID, result.get(0).getCategoryId());
        assertEquals(TEST_CATEGORY_NAME, result.get(0).getCategoryName());
    }

}