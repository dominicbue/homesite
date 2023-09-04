package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.adapterpersistence.AbstractRepository;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeMapper;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import domin.homesite.cookbook.recipemanagement.gateway.IRecipeRepository;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity.PARAMETER_RECIPE_NAME;
import static domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity.SEARCH_RECIPE_WITH_NAME;

@Log4j2
public class RecipeRepositoryImpl extends AbstractRepository<RecipeEntity> implements IRecipeRepository {

    private final RecipeMapper recipeMapper;
    private final EntityMergeHelper mergeHelper;

    public RecipeRepositoryImpl(RecipeMapper recipeMapper, EntityMergeHelper entityMergeHelper) {
        this.recipeMapper = recipeMapper;
        this.mergeHelper = entityMergeHelper;
    }

    public void upsertRecipe(@NonNull Recipe recipe) {
        RecipeEntity entity;
        Optional<RecipeEntity> optional = find(RecipeEntity.class, recipe.getRecipeId());
        if (optional.isPresent()) {
            entity = optional.get();
        } else {
            entity = new RecipeEntity();
        }
        recipeMapper.mapDomainToEntity(recipe, entity);
        mergeHelper.mergeWithExistingEntities(entity);
        persist(entity);
    }

    public List<Recipe> searchRecipeByName(@NonNull String recipeName) {
        final TypedQuery<RecipeEntity> query = createNamedQuery(SEARCH_RECIPE_WITH_NAME, RecipeEntity.class);
        query.setParameter(PARAMETER_RECIPE_NAME, recipeName);
        log.info("SQL-Statement von " + SEARCH_RECIPE_WITH_NAME + " : " + query + " Parameter - Value : " + query.getParameterValue(PARAMETER_RECIPE_NAME));
        return query.getResultStream()
                .map(recipeMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }
}
