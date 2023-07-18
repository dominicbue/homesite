package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeMapper;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static domin.homesite.cookbook.adapterpersistence.factories.DomainTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class RecipeHeaderMapperTest {

    private RecipeMapper testee;

    @BeforeEach
    void setup(){
        testee = new RecipeMapper();
    }

    @Test
    void mapDomainToEntity_when_newRecipe_then_returnRecipeEntity() {
        //arrange
        Recipe input = dummyRecipeBuilder().build();
        RecipeEntity output = new RecipeEntity();

        //act
        testee.mapDomainToEntity(input, output);

        //assert
        assertEquals(DOMAIN_RECIPE_NAME, output.getName());
        assertEquals(DOMAIN_RECIPE_ID_0, output.getObject_Id());
        assertEquals(DOMAIN_RECIPE_PICTURE, output.getPicture());
    }

    @Test
    void mapDomainToEntity_when_newRecipeJustWithName_then_returnRecipeEntity() {
        //arrange
        Recipe input = dummyRecipeBuilder()
                .withPicture(null)
                .build();
        RecipeEntity output = new RecipeEntity();

        //act
        testee.mapDomainToEntity(input, output);

        //assert
        assertEquals(DOMAIN_RECIPE_NAME, output.getName());
        assertEquals(DOMAIN_RECIPE_ID_0, output.getObject_Id());
        assertNull(output.getPicture());

    }
}