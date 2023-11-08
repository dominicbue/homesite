package domin.homesite.cookbook.recipemanagement.application;

import domin.homesite.cookbook.recipemanagement.application.recipe.RecipeLogic;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import domin.homesite.cookbook.recipemanagement.gateway.dto.RecipeDto;
import domin.homesite.cookbook.recipemanagement.gateway.mapper.RecipeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static domin.homesite.cookbook.recipemanagement.domain.DomainTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipManagerFacadeTest {

    @Mock
    private RecipeLogic recipeLogic;
    private RecipeMapper recipeMapper;

    private RecipeManagerFacade testee;

    @Captor
    private ArgumentCaptor<Recipe> recipeCaptor;


    @BeforeEach
    void setUp() {
        recipeMapper = new RecipeMapper();
        testee = new RecipeManagerFacade(recipeLogic, recipeMapper);
    }

    @Test
    void saveRecipe_when_RecipeDto_thenSaveRecipeWithRecipe() {
        //arrange
        RecipeDto recipeDto = RecipeDto.builder()
                .recipeId(DUMMY_RECIPE_ID)
                .recipeTitle(DUMMY_RECIPE_NAME)
                .categoryId(DUMMY_CATEGORY_ID)
                .categoryName(DUMMY_CATEGORIE_NAME)
                .build();

        //act
        testee.saveRecipe(recipeDto);

        //assert
        verify(recipeLogic).saveRecipe(recipeCaptor.capture());
        Recipe captorValue = recipeCaptor.getValue();
        assertEquals(DUMMY_RECIPE_ID, captorValue.getRecipeId());
        assertEquals(DUMMY_RECIPE_NAME, captorValue.getRecipeName());
        assertEquals(DUMMY_CATEGORY_ID, captorValue.getCategory().getCategoryId());
        assertEquals(DUMMY_CATEGORIE_NAME, captorValue.getCategory().getCategoryName());
    }

    @Test
    void getAllRecipes_when_noExistingRecipe_thenReturnEmptyList() {
        // arrange
        when(recipeLogic.getAllRecipes()).thenReturn(Collections.emptyList());

        //act
        List<RecipeDto> expectedDtos = testee.getAllRecipes();

        //assert
        assertTrue(expectedDtos.isEmpty());
    }

    @Test
    void getAllRecipes_when_oneRecipeExists_thenReturnList() {
        // arrange
        Recipe dummyRecipe = dummyRecipeBuilder().build();
        when(recipeLogic.getAllRecipes()).thenReturn(Collections.singletonList(dummyRecipe));

        //act
        List<RecipeDto> expectedDtos = testee.getAllRecipes();

        //assert
        assertEquals(1, expectedDtos.size());
        RecipeDto result = expectedDtos.get(0);
        assertEquals(DUMMY_RECIPE_ID, result.getRecipeId());
        assertEquals(DUMMY_RECIPE_NAME, result.getRecipeTitle());
        assertEquals(DUMMY_CATEGORY_ID, result.getCategoryId());
        assertEquals(DUMMY_CATEGORIE_NAME, result.getCategoryName());
    }

}