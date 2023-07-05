package domin.homesite.cookbook.recipemanagement.domain;

public class CategoryBuilder {

    String categoryId;
    String categoryName;

    public CategoryBuilder withCategoryId(String categoryId){
        this.categoryId = categoryId;
        return this;
    }

    public CategoryBuilder withCategoryName(String categoryName){
        this.categoryName = categoryName;
        return this;
    }

    public Category build() { return new Category(this);}
}
