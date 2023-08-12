package domin.homesite.cookbook.adapterpersistence;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeMapper;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import domin.homesite.cookbook.recipemanagement.gateway.IRecipeRepository;
import lombok.NonNull;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity.PARAMETER_RECIPE_NAME;
import static domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity.SEARCH_RECIPE_WITH_NAME;

public class RecipeRepositoryImpl extends AbstractRepository<RecipeEntity> implements IRecipeRepository {

    private final RecipeMapper recipeMapper;

    public RecipeRepositoryImpl(RecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }

    public void upsertRecipe(@NonNull Recipe recipe) {
        //Todo:
        RecipeEntity entity = new RecipeEntity();
        Optional<RecipeEntity> optional = find(RecipeEntity.class, recipe.getRecipeId());
        boolean updateEntity = false;
        if (optional.isPresent()) {
            entity = optional.get();
            updateEntity = true;
        }
        recipeMapper.mapDomainToEntity(recipe, entity);
        persist(entity, updateEntity);
    }

    public List<Recipe> searchRecipeByName(@NonNull String recipeName) {
        final TypedQuery<RecipeEntity> query = createNamedQuery(SEARCH_RECIPE_WITH_NAME, RecipeEntity.class);
        query.setParameter(PARAMETER_RECIPE_NAME, recipeName);
        return query.getResultStream()
                .map(recipeMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    public List<Recipe> readAllRecipes() {
        return Collections.emptyList();
    }
}
