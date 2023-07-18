package domin.homesite.cookbook.adapterpersistence.domain.ingredients;

import lombok.NonNull;

public class IngredientEntityId {
    @NonNull
    private String object_Id;
    @NonNull
    private String name;
    @NonNull
    private String quantity;
    @NonNull
    private String unit;
}
