package domin.homesite.cookbook.adapterpersistence.domain.category;

import domin.homesite.cookbook.recipemanagement.domain.Category;

public class CategoryMapper {

    public CategoryEntity mapDomainToEntity(Category category, CategoryEntity entity) {
        entity.setObject_Id(category.getCategoryId());
        entity.setName(category.getCategoryName());
        return entity;
    }
}
