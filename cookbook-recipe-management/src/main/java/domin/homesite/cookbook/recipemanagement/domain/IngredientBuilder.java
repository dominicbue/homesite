package domin.homesite.cookbook.recipemanagement.domain;

public class IngredientBuilder {

   String ingredientId;
    String ingredientName;
    String quantity;
    IngredientsUnit unit;

    public IngredientBuilder withIngredientId(String ingredientId){
        this.ingredientId = ingredientId;
        return this;
    }

    public IngredientBuilder withIngredientName(String ingredientName){
        this.ingredientName = ingredientName;
        return this;
    }

    public IngredientBuilder withQuantity(String quantity){
        this.quantity = quantity;
        return this;
    }

    public IngredientBuilder withIngredientsUnit(IngredientsUnit unit){
        this.unit = unit;
        return this;
    }

    public Ingredient build() { return new Ingredient(this);}
}
