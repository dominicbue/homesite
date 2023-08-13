package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.adapterpersistence.AbstractRepositoryTest;
import domin.homesite.cookbook.adapterpersistence.RecipeRepositoryImpl;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeMapper;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import org.dbunit.DatabaseUnitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static domin.homesite.cookbook.recipemanagement.domain.DomainTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeRepositoryImplTest extends AbstractRepositoryTest {

    private RecipeRepositoryImpl testee;

    @BeforeEach
    void setup() {
        RecipeMapper recipeMapper = new RecipeMapper();
        testee = new RecipeRepositoryImpl(recipeMapper);
        injectEntityManagerToClass(testee);
    }
    @Test
    void upsertRecipe_when_newRecipeWithNewName_then_noExceptionThrown() {
        //arrange
        Recipe expectedRecipe = dummyRecipeBuilder().build();

        //act + assert
        transactionBegin();
        testee.upsertRecipe(expectedRecipe);
        transactionCommit();
    }

    @Test
    void upsertRecipe_when_recipeWithSameIdInDbAndOtherInstructions_then_upsertRecipeWithIdOfDbAndDataFromRecipe() throws SQLException, DatabaseUnitException, IOException {
        //arrange
        importDbUnitFile("dbunit_dummyRecipe.xml");
        Recipe expectedRecipe = dummyRecipeBuilder().build();

        //act
        transactionBegin();
        testee.upsertRecipe(expectedRecipe);
        transactionCommit();

        //assert
        transactionBegin();
        RecipeEntity upsertedEntity = em.find(RecipeEntity.class, RECIPE_ID);
        transactionCommit();

        assertEquals(RECIPE_ID, upsertedEntity.getRecipe_id());
        assertEquals(2, upsertedEntity.getInstructionEntities().size());
        assertEquals(INSTRUCTION_ID_INS01, upsertedEntity.getInstructionEntities().get(0).getInstruction_id());
        assertEquals(INSTRUCTION_DO_THIS, upsertedEntity.getInstructionEntities().get(0).getDescription());
        assertEquals(INSTRUCTION_ID_INS02, upsertedEntity.getInstructionEntities().get(1).getInstruction_id());
        assertEquals(INSTRUCTION_AND_THIS, upsertedEntity.getInstructionEntities().get(1).getDescription());
        assertEquals(DUMMY_CATEGORIE_NAME, upsertedEntity.getCategoryEntity().getName());
        assertEquals(2, upsertedEntity.getIngredientEntities().size());
        assertEquals(INGREDIENT_APFEL, upsertedEntity.getIngredientEntities().get(0).getName());
        assertEquals(INGREDIENT_MEHL, upsertedEntity.getIngredientEntities().get(1).getName());

    }




}