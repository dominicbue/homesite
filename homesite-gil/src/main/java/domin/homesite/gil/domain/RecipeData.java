package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class RecipeData {

    private List<Ingredient> ingredients;
    private List<Instruction> instructions;

}
