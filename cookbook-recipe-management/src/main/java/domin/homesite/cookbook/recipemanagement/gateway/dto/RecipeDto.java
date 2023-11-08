package domin.homesite.cookbook.recipemanagement.gateway.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class RecipeDto {

    private String recipeId;
    @NonNull
    private String recipeTitle;
    private byte[] recipePicture;
    private String categoryId;
    @NonNull
    private String categoryName;
}
