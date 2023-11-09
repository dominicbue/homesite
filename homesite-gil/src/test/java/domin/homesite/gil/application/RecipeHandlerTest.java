package domin.homesite.gil.application;

import domin.homesite.gil.adapter.recipemanagement.RecipeManagementAdapterImpl;
import domin.homesite.gil.domain.Recipe;
import domin.homesite.gil.domain.RecipeHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static domin.homesite.gil.GilDomainTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeHandlerTest {

    @Mock
    private RecipeManagementAdapterImpl recipeManagementAdapter;

    private RecipeHandler testee;

    @BeforeEach
    void setUp() {
        testee = new RecipeHandler(recipeManagementAdapter);
    }

    @Test
    void getAllRecipeHeaders_whenEmptyRecipeList_thenReturnEmptyList(){
        //arrange
        when(recipeManagementAdapter.getAllRecipes()).thenReturn(Collections.emptyList());

        //act
        List<RecipeHeader> resultList = testee.getAllRecipeHeaders();

        //assert
        assertTrue(resultList.isEmpty());
    }

    @Test
    void getAllRecipeHeaders_whenRecipeExists_thenReturnRecipeList(){
        //arrange
        Recipe expectedRecipe = dummyGilRecipe();
        when(recipeManagementAdapter.getAllRecipes()).thenReturn(Collections.singletonList(expectedRecipe));

        //act
        List<RecipeHeader> resultList = testee.getAllRecipeHeaders();

        //assert
        assertEquals(1, resultList.size());
        RecipeHeader resultHeader = resultList.get(0);
        assertEquals(DUMMY_GIL_RECIPE_ID, resultHeader.getRecipeId());
        assertEquals(DUMMY_GIL_RECIPE_NAME, resultHeader.getRecipeTitle());
        assertEquals(DUMMY_GIL_CATEGORY_ID, resultHeader.getRecipeCategory().getCategoryId());
        assertEquals(DUMMY_GIL_CATEGORY_NAME, resultHeader.getRecipeCategory().getCategoryName());
    }

}