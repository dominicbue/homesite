package domin.homesite.cookbook.recipemanagement.domain;

import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.requireNonNull;

@Setter
@Getter
public class Category {
    private String categoryId;
    private String categoryName;

    public Category(CategoryBuilder builder){
        this.categoryId = builder.categoryId;
        this.categoryName = requireNonNull(builder.categoryName, "CategoryName darf nicht null sein");
    }
}
