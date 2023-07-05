package domin.homesite.cookbook.adapterpersistence.domain;

import domin.homesite.cookbook.recipemanagement.domain.Category;

import javax.inject.Inject;

public class CategoryMapper {
    @Inject
    private CategoryEntity entity;
    public CategoryEntity mapDomainToEntity(Category category) {
        entity.setObject_Id(category.getCategoryId());
        entity.setName(category.getCategoryName());
        return entity;
    }
}
