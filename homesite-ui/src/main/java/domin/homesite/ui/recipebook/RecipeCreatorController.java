package domin.homesite.ui.recipebook;

import domin.homesite.gil.application.RecipeHandler;
import domin.homesite.gil.domain.*;
import lombok.Getter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SessionScoped
@Named
public class RecipeCreatorController implements Serializable {


    @Getter
    private String recipeName;
    @Getter
    private Category recipeCategory;
    @Getter
    private String newCategoryName;
    private boolean isCategoryDuplicate;
    private List<Category> persistedCategories;
    @Getter
    private List<Ingredient> recipeIngredients;
    @Getter
    private String newIngredientName;
    @Getter
    private String newIngredientQuantity;
    @Getter
    private String selectedUnit;

    @Getter
    private List<Instruction> recipeInstructions;
    @Getter
    private String newInstruction;


    @Inject
    private RecipeHandler recipeHandler;

    
    public RecipeCreatorController() {
        this.persistedCategories = new ArrayList<>();
        this.recipeIngredients = new ArrayList<>();
        this.recipeInstructions = new ArrayList<>();
    }

    public void setRecipeName(String newRecipeName) {
        this.recipeName = newRecipeName;
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
            this.recipeCategory = Category.builder().categoryId("new").categoryName(newCategoryName).build();
        }
    }

    public boolean getIsCategoryDuplicate() {
        return this.isCategoryDuplicate;
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

    public void setRecipeIngredients(List<Ingredient> ingredients) {
        this.recipeIngredients = ingredients;
    }

    public void setNewIngredientName(String name) {
        this.newIngredientName = name;
    }

    public void setNewIngredientQuantity(String quantity) {
        this.newIngredientQuantity = quantity;
    }

    public void setSelectedUnit(String unitName) {
        this.selectedUnit = unitName;
    }

    public void setRecipeInstructions(List<Instruction> instructions) {
        this.recipeInstructions = instructions;
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
