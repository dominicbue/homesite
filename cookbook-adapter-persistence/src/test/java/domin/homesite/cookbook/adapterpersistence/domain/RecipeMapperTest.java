package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static domin.homesite.cookbook.adapterpersistence.PersistenceTestDataFactory.dummyRecipeEnitity;
import static domin.homesite.cookbook.recipemanagement.utils.TestDataFactory.dummyRecipeBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RecipeMapperTest {

    private RecipeMapper testee;

    @BeforeEach
    void init(){
        CategoryMapper categoryMapper = new CategoryMapper();
        IngredientMapper ingredientMapper = new IngredientMapper();
        InstructionMapper instructionMapper = new InstructionMapper();
        testee = new RecipeMapper(ingredientMapper, instructionMapper, categoryMapper);
    }

    @Test
    void mapDomainToEntity_when_newRecipe_then_returnRecipeEntity() {
        //arrange
        Recipe input = dummyRecipeBuilder().build();
        RecipeEntity output = new RecipeEntity();
        RecipeEntity expected = dummyRecipeEnitity();

        //act
        testee.mapDomainToEntity(input, output);

        //assert
        assertEquals(expected.getName(), output.getName());
    }
}