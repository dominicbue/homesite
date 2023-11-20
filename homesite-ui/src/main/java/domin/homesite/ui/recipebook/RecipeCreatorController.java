package domin.homesite.ui.recipebook;

import domin.homesite.gil.domain.Category;
import domin.homesite.gil.domain.Ingredient;
import domin.homesite.gil.domain.IngredientsUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SessionScoped
@ManagedBean(name = "recipeCreatorController")
public class RecipeCreatorController implements Serializable {


    private String recipeName;
    private Category recipeCategory;

    private List<Category> persistedCategories;
    private boolean isCategoryDuplicate;

    private List<Ingredient> recipeIngredients;
    private String newIngredientName;
    private String newIngredientQuantity;
    private String selectedUnit;






    public RecipeCreatorController() {
        Category category1 = Category.builder().categoryId("cat01").categoryName("Frühstück").build();
        Category category2 = Category.builder().categoryId("cat02").categoryName("Abendessen").build();
        persistedCategories = Arrays.asList(category1, category2);
        recipeIngredients = new ArrayList<>();
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Category getRecipeCategory() {
        if(recipeCategory == null){
            return Category.builder().categoryId(null).categoryName("-- Wähle eine Kategorie --").build();
        }
        return recipeCategory;
    }

    public void setRecipeCategory(Category recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public void setNewCategoryName(String newCategoryName) {
        if (persistedCategories.stream().map(Category::getCategoryName).anyMatch(str -> str.equals(newCategoryName))) {
            isCategoryDuplicate = true;
        } else {
            isCategoryDuplicate = false;
            this.recipeCategory = Category.builder().categoryId(null).categoryName(newCategoryName).build();
        }
    }

    public boolean isCategoryDuplicate() {
        return isCategoryDuplicate;
    }

    public String submitNewCategory() {
        return null;
    }

    public List<Category> getPersistedCategories() {
        return persistedCategories;
    }
    public List<String> getIngredientUnits() {
        return Arrays.stream(IngredientsUnit.values()).map(IngredientsUnit::getValue).collect(Collectors.toList());
    }
    public List<Ingredient> getRecipeIngredients(){
        return recipeIngredients;
    }


    public void setNewIngredientName(String ingredientName){
        this.newIngredientName = ingredientName;
    }

    public String getNewIngredientName() {
        return newIngredientName;
    }

    public void setNewIngredientQuantity(String quantity){
        this.newIngredientQuantity = quantity;
    }

    public String getNewIngredientQuantity() {
        return newIngredientQuantity;
    }

    public void setSelectedUnit(String selectedUnit){
        this.selectedUnit = selectedUnit;
    }

    public String getSelectedUnit() {
        return selectedUnit;
    }

    public String addIngredient() {
        if(newIngredientQuantity == null || selectedUnit == null || newIngredientName == null){
            return null;
        }
        Ingredient newIngredient = Ingredient.builder()
                .quantity(newIngredientQuantity)
                .unit(IngredientsUnit.fromValue(selectedUnit))
                .ingredientName(newIngredientName)
                .build();
        recipeIngredients.add(newIngredient);
        return null;
    }
}
