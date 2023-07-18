package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity;
import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryMapper;
import domin.homesite.cookbook.recipemanagement.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static domin.homesite.cookbook.adapterpersistence.factories.DomainTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    private CategoryMapper testee;

    @BeforeEach
    void setup() {
        testee = new CategoryMapper();
    }

    @Test
    void mapDomainToEntity_when_IDandNameSet_then_returnCategoryEntity(){
        //arrange
        Category input = dummyCategory();
        CategoryEntity output = new CategoryEntity();

        //act
        testee.mapDomainToEntity(input, output);

        //assert
        assertEquals(DOMAIN_CATEGORIE_ID, output.getObject_Id());
        assertEquals(DUMMY_CATEGORIE_NAME, output.getName());
    }


}