package domin.homesite.cookbook.adapterpersistence.domain.ingredients;

import domin.homesite.cookbook.adapterpersistence.AbstractRepository;
import domin.homesite.cookbook.recipemanagement.gateway.IIngredientRepository;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.Optional;

import static domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity.*;
import static java.lang.String.format;

@Log4j2
public class IngredientRepositoryImpl extends AbstractRepository<IngredientEntity> implements IIngredientRepository {

    private IngredientMapper ingredientMapper;

    @Inject
    public IngredientRepositoryImpl(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public Optional<IngredientEntity> findIdenticalPersistedIngredient(IngredientEntity ingredient) {
        final TypedQuery<IngredientEntity> query = createNamedQuery(FIND_IDENTICAL_INGREDIENT, IngredientEntity.class);
        query.setParameter(PARAMETER_INGREDIENT_NAME, ingredient.getName());
        query.setParameter(PARAMETER_INGREDIENT_QUANTITY, ingredient.getQuantity());
        query.setParameter(PARAMETER_INGREDIENT_UNIT, ingredient.getUnit());
        log.info(format("SQL-Statement von %s : %s mit 'Name : %s', 'Quantity : %s', 'Unit : %s",
                FIND_IDENTICAL_INGREDIENT,
                query,query.getParameterValue(PARAMETER_INGREDIENT_NAME),
                query.getParameterValue(PARAMETER_INGREDIENT_QUANTITY),
                query.getParameterValue(PARAMETER_INGREDIENT_UNIT)));
        return Optional.of(query.getSingleResult());
    }
}
