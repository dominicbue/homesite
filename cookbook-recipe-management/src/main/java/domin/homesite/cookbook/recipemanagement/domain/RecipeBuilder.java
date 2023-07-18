package domin.homesite.cookbook.recipemanagement.domain;

import java.util.List;

public class RecipeBuilder {

    String recipeId;
    String recipeName;
    byte[] recipePicture;
    List<Instruction> recipeInstructions;
    Category category;
    List<Ingredient> ingredients;

    public RecipeBuilder withRecipeId(String id){
        this.recipeId = id;
        return this;
    }

    public RecipeBuilder withRecipeName(String name){
        this.recipeName = name;
        return this;
    }

    public RecipeBuilder withPicture(byte[] recipePicture){
        this.recipePicture = recipePicture;
        return this;
    }

    public RecipeBuilder withInstructions(List<Instruction> instructions){
        this.recipeInstructions = instructions;
        return this;
    }

    public RecipeBuilder withCategory(Category category){
        this.category = category;
        return this;
    }

    public RecipeBuilder withIngredients(List<Ingredient> ingredients){
        this.ingredients = ingredients;
        return this;
    }

    public Recipe build() {return new Recipe(this);}

}
