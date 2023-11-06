package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder(toBuilder = true)
public class RecipeData {

    private Set<Ingredient> ingredients;
    private Set<Instruction> instructions;

}
