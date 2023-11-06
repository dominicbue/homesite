package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder(toBuilder = true)
public class Recipe {

    @NonNull
    private RecipeHeader header;
    private RecipeData data;

}
