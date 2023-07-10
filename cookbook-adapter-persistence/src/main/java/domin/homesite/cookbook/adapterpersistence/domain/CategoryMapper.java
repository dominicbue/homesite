package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.recipemanagement.domain.Category;

import javax.inject.Inject;

public class CategoryMapper {

    public CategoryEntity mapDomainToEntity(Category category, CategoryEntity entity) {
        entity.setObject_Id(category.getCategoryId());
        entity.setName(category.getCategoryName());
        return entity;
    }
}
