package domin.homesite.gil;

import domin.homesite.gil.domain.Category;
import domin.homesite.gil.domain.Recipe;
import domin.homesite.gil.domain.RecipeHeader;

public class GilDomainTestDataFactory {

    public static final String DUMMY_GIL_CATEGORY_ID = "CatGil01";
    public static final String DUMMY_GIL_CATEGORY_NAME = "Category Gil Dummy";
    public static final String DUMMY_GIL_RECIPE_ID = "RecGil01";
    public static final String DUMMY_GIL_RECIPE_NAME = "Recipe Gil Dummy";

    public static Category dummyGilCategory() {
        return Category.builder()
                .categoryId(DUMMY_GIL_CATEGORY_ID)
                .categoryName(DUMMY_GIL_CATEGORY_NAME)
                .build();
    }

    public static RecipeHeader dummyRecipeHeader() {
        return RecipeHeader.builder()
                .recipeId(DUMMY_GIL_RECIPE_ID)
                .recipeTitle(DUMMY_GIL_RECIPE_NAME)
                .recipeCategory(dummyGilCategory())
                .build();
    }

    public static Recipe dummyGilRecipe() {
        return Recipe.builder()
                .header(dummyRecipeHeader())
                .data(null)
                .build();
    }
}
