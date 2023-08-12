package domin.homesite.cookbook.adapterpersistence.domain.category;

import domin.homesite.cookbook.recipemanagement.domain.Category;

public class CategoryMapper {

    public void mapDomainToEntity(Category category, CategoryEntity entity) {
        entity.setName(category.getCategoryName());

        if(entity.getCategory_oid() == null){
            entity.setCategory_oid(category.getCategoryId());
        }
    }
}
