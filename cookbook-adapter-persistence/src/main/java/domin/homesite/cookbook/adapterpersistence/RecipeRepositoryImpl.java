package domin.homesite.cookbook.adapterpersistence;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeMapper;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import domin.homesite.cookbook.recipemanagement.gateway.IRecipeRepository;
import lombok.NonNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@PersistenceContext(name = "recipemanagment")
public class RecipeRepositoryImpl implements IRecipeRepository {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    @Inject
    private RecipeMapper recipeMapper;

    public RecipeRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("recipemanagement");
        em = emf.createEntityManager();
    }

    @Override
    public void upsertRecipe(@NonNull Recipe recipe) {
        //Todo:
        RecipeEntity entity;
        if (recipe.getRecipeId() == null) {
            entity =  new RecipeEntity();
        } else {
            entity = em.find(RecipeEntity.class, recipe.getRecipeId());
        }
        recipeMapper.mapDomainToEntity(recipe, entity);
        em.persist(entity);
    }

    @Override
    public List<Recipe> readAllRecipes() {
     return Collections.emptyList();
    }
}
