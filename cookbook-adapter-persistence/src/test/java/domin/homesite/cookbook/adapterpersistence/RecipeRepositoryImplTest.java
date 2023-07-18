package domin.homesite.cookbook.adapterpersistence;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import domin.homesite.cookbook.recipemanagement.domain.Recipe;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static domin.homesite.cookbook.adapterpersistence.factories.DomainTestDataFactory.DOMAIN_RECIPE_NAME;
import static domin.homesite.cookbook.adapterpersistence.factories.DomainTestDataFactory.dummyRecipeBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@AllArgsConstructor(onConstructor_ = {@Inject})
@ExtendWith(MockitoExtension.class)
class RecipeRepositoryImplTest {

    @Mock
    private final EntityManager em;
    @Mock
    private final EntityManagerFactory emf;
    private RecipeRepositoryImpl testee;
    private ArgumentCaptor<RecipeEntity> recipeCaptor;

    @BeforeEach
    void setup() {
        when(emf.createEntityManager()).thenReturn(em);
        testee = new RecipeRepositoryImpl();
        recipeCaptor = ArgumentCaptor.forClass(RecipeEntity.class);
    }

    @Test
    void upsertRecipe_when_recipeIdIsNull_then_upsertNewEntity() {
        //arrange
        Recipe dummy = dummyRecipeBuilder().build();

        //act
        testee.upsertRecipe(dummy);

        //assert
        verify(em).persist(recipeCaptor.capture());
        assertEquals(DOMAIN_RECIPE_NAME, recipeCaptor.getValue().getName());
    }


}