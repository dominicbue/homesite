package domin.homesite.gil.adapter.recipemanagement;

import domin.homesite.cookbook.recipemanagement.application.RecipeManagerFacade;
import domin.homesite.cookbook.recipemanagement.gateway.dto.RecipeDto;
import domin.homesite.gil.domain.Recipe;
import domin.homesite.gil.domain.RecipeHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static domin.homesite.gil.GilDomainTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeManagementAdapterImplTest {

    @Mock
    private RecipeManagerFacade recipeManagerFacade;
    @Captor
    private ArgumentCaptor<RecipeDto> dtoCaptor;

    private RecipeManagementAdapterImpl testee;

    @BeforeEach
    void setUp() {
        testee = new RecipeManagementAdapterImpl(recipeManagerFacade);
    }

    @Test
    void saveRecipeDataFromUi_whenOnlyHeaderExists_ThenSaveDto() {
        //arrange
        RecipeHeader savingHeader = dummyRecipeHeader();

        //act
        testee.saveRecipeDataFromUi(savingHeader, null);

        //assert
        verify(recipeManagerFacade).saveRecipe(dtoCaptor.capture());
        RecipeDto captorResult = dtoCaptor.getValue();
        assertEquals(DUMMY_GIL_RECIPE_ID, captorResult.getRecipeId());
        assertEquals(DUMMY_GIL_RECIPE_NAME, captorResult.getRecipeTitle());
        assertEquals(DUMMY_GIL_CATEGORY_ID, captorResult.getCategoryId());
        assertEquals(DUMMY_GIL_CATEGORY_NAME, captorResult.getCategoryName());
    }

    @Test
    void getAllRecipes_whenRecipeDtoEmpty_thenReturnEmptyList() {
        //arrange
        when(recipeManagerFacade.getAllRecipes()).thenReturn(Collections.emptyList());

        //act
        List<Recipe> resultList = testee.getAllRecipes();

        //assert
        assertTrue(resultList.isEmpty());
    }

    @Test
    void getAllRecipes_whenRecipeDto_thenReturnListOfRecipe() {
        //arrange
        RecipeDto expectedDto = RecipeDto.builder()
                .recipeId(DUMMY_GIL_RECIPE_ID)
                .recipeTitle(DUMMY_GIL_RECIPE_NAME)
                .categoryId(DUMMY_GIL_CATEGORY_ID)
                .categoryName(DUMMY_GIL_CATEGORY_NAME)
                .build();
        when(recipeManagerFacade.getAllRecipes()).thenReturn(Collections.singletonList(expectedDto));

        //act
        List<Recipe> resultList = testee.getAllRecipes();

        //assert
        assertEquals(1, resultList.size());
        RecipeHeader resultHeader = resultList.get(0).getHeader();
        assertEquals(DUMMY_GIL_RECIPE_ID, resultHeader.getRecipeId());
        assertEquals(DUMMY_GIL_RECIPE_NAME, resultHeader.getRecipeTitle());
        assertEquals(DUMMY_GIL_CATEGORY_ID, resultHeader.getRecipeCategory().getCategoryId());
        assertEquals(DUMMY_GIL_CATEGORY_NAME, resultHeader.getRecipeCategory().getCategoryName());
    }

}