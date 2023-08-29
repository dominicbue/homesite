package domin.homesite.cookbook.recipemanagement.domain;

import java.util.Set;

public class RecipeBuilder {

    String recipeId;
    String recipeName;
    byte[] recipePicture;
    Set<Instruction> recipeInstructions;
    Category category;
    Set<Ingredient> ingredients;

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

    public RecipeBuilder withInstructions(Set<Instruction> instructions){
        this.recipeInstructions = instructions;
        return this;
    }

    public RecipeBuilder withCategory(Category category){
        this.category = category;
        return this;
    }

    public RecipeBuilder withIngredients(Set<Ingredient> ingredients){
        this.ingredients = ingredients;
        return this;
    }

    public Recipe build() {return new Recipe(this);}

}
