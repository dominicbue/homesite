package domin.homesite.ui.recipebook;

import domin.homesite.gil.application.RecipeHandler;
import domin.homesite.gil.domain.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SessionScoped
@Named(value = "recipeCreatorController")
public class RecipeCreatorController implements Serializable {


    private String recipeName;
    private Category recipeCategory;
    private String newCategoryName;
    private boolean isCategoryDuplicate;
    private List<Category> persistedCategories;
    private List<Ingredient> recipeIngredients;
    private String newIngredientName;
    private String newIngredientQuantity;
    private String selectedUnit;

    private List<Instruction> recipeInstructions;
    private String newInstruction;


    @Inject
    private RecipeHandler recipeHandler;

    
    public RecipeCreatorController() {
        this.persistedCategories = new ArrayList<>();
        this.recipeIngredients = new ArrayList<>();
        this.recipeInstructions = new ArrayList<>();
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String newRecipeName) {
        this.recipeName = newRecipeName;
    }

    public Category getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(Category selectedCategory) {
        this.recipeCategory = selectedCategory;
    }

    public void setNewCategoryName(String newCategoryName) {
        if (persistedCategories.stream().map(Category::getCategoryName).anyMatch(str -> str.equals(newCategoryName))) {
            isCategoryDuplicate = true;
        } else {
            isCategoryDuplicate = false;
            this.newCategoryName = newCategoryName;
            this.recipeCategory = Category.builder().categoryId(null).categoryName(newCategoryName).build();
        }
    }

    public String getNewCategoryName() {
        return this.newCategoryName;
    }

    public boolean getIsCategoryDuplicate() {
        return this.isCategoryDuplicate;
    }

    public void setIsCategoryDuplicate(boolean value) {
        this.isCategoryDuplicate = value;
    }

    public List<Category> getPersistedCategories() {
        List<RecipeHeader> persistedHeaders = recipeHandler.getAllRecipeHeaders();
        if(persistedHeaders != null) {
            this.persistedCategories = persistedHeaders.stream()
                    .map(RecipeHeader::getRecipeCategory)
                    .collect(Collectors.toList());
        }
        return persistedCategories;
    }

    public void setPersistedCategories(List<Category> categories) {
        this.persistedCategories = categories;
    }

    public List<Ingredient> getRecipeIngredients() {
        return this.recipeIngredients;
    }

    public void setRecipeIngredients(List<Ingredient> ingredients) {
        this.recipeIngredients = ingredients;
    }

    public String getNewIngredientName() {
        return this.newIngredientName;
    }

    public void setNewIngredientName(String name) {
        this.newIngredientName = name;
    }

    public String getNewIngredientQuantity() {
        return this.newIngredientQuantity;
    }

    public void setNewIngredientQuantity(String quantity) {
        this.newIngredientQuantity = quantity;
    }

    public String getSelectedUnit() {
        return this.selectedUnit;
    }

    public void setSelectedUnit(String unitName) {
        this.selectedUnit = unitName;
    }

    public List<Instruction> getRecipeInstructions() {
        return this.recipeInstructions;
    }

    public void setRecipeInstructions(List<Instruction> instructions) {
        this.recipeInstructions = instructions;
    }

    public String getNewInstruction() {
        return this.newInstruction;
    }

    public void setNewInstruction(String newInstruction) {
        this.newInstruction = newInstruction;
    }

    /*
    Methoden die ohne Attribute arbeiten. (z.B. actions von CommandButtons)
     */
    public String addCategory() {
        return null;
    }

    public List<String> getIngredientUnits() {
        return Arrays.stream(IngredientsUnit.values()).map(IngredientsUnit::getValue).collect(Collectors.toList());
    }


    public String addIngredient() {
        if (newIngredientQuantity == null || selectedUnit == null || newIngredientName == null) {
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

    public String addInstruction() {
        if (newInstruction == null) {
            return null;
        }
        Instruction addedInstruction = Instruction.builder()
                .instructionId(null)
                .instructionText(newInstruction)
                .build();
        recipeInstructions.add(addedInstruction);
        return null;
    }

    public String saveRecipe() {
        RecipeHeader recipeHeader = RecipeHeader.builder()
                .recipeId(null)
                .recipeTitle(recipeName)
                .recipePicture(null)
                .recipeCategory(recipeCategory)
                .build();
        RecipeData recipeData = RecipeData.builder()
                .ingredients(recipeIngredients)
                .instructions(recipeInstructions)
                .build();
        recipeHandler.persistRecipeFromUi(recipeHeader, recipeData);
        return null;
    }
}
