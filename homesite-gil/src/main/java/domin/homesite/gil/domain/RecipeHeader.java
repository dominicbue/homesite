package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;


@Getter
@Builder(toBuilder = true)
public class RecipeHeader {

    private String recipeId;
    @NonNull
    private String recipeTitle;
    private byte[] recipePicture;
    @NonNull
    private Category recipeCategory;

}
