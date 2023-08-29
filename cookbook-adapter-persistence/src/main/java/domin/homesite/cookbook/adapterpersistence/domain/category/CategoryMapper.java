package domin.homesite.cookbook.adapterpersistence.domain.category;

import domin.homesite.cookbook.recipemanagement.domain.Category;
import domin.homesite.cookbook.recipemanagement.domain.CategoryBuilder;

public class CategoryMapper {

    public void mapDomainToEntity(Category category, CategoryEntity entity) {
        if (entity.getCategory_oid() == null) {
            entity.setCategory_oid(category.getCategoryId());
        }
        entity.setName(category.getCategoryName());
    }

    public Category mapEntityToDomain(CategoryEntity categoryEntity) {
        return new CategoryBuilder()
                .withCategoryId(categoryEntity.getCategory_oid())
                .withCategoryName(categoryEntity.getName())
                .build();
    }
}
