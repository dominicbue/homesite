package domin.homesite.cookbook.recipemanagement.gateway.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeDto {
    private String recipeId;
    private String recipeTitle;

    private byte[] recipePicture;

    private String categoryId;
    private String categoryName;
}
