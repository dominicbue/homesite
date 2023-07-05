package domin.homesite.cookbook.recipemanagement.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Setter
@Getter
public class Recipe {

    private String recipeId;
    private String recipeName;
    private byte[] recipePicture;
    private List<Instruction> recipeInstructions;
    private Category category;
    private List<Ingredient> ingredients;

    public Recipe(RecipeBuilder builder) {
    this.recipeId = requireNonNull(builder.recipeId, "RecipeId darf nicht null sein");
    this.recipeName = requireNonNull(builder.recipeName, "RecipeName darf nicht null sein");
    this.recipeInstructions = builder.recipeInstructions;
    this.recipePicture = builder.recipePicture;
    this.category = builder.category;
    this.ingredients = requireNonNull(builder.ingredients, "Ingredients darf nicht null sein");
    }
}
