package domin.homesite.cookbook.adapterpersistence.domain.category;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

import static domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity.COUNT_CATEGORIES;
import static domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity.TABLENAME;

@MappedSuperclass
@Entity
@Table(name = TABLENAME)
@Data
@NamedQueries(
        @NamedQuery(name = COUNT_CATEGORIES,
                query = "SELECT count(c) FROM CategoryEntity c")
)
public class CategoryEntity {

    public static final String TABLENAME = "CATEGORIES";
    public static final String COUNT_CATEGORIES= "countCategories";

    @Id
    @Column(name = "CATEGORY_OID")
    private String category_oid;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "categoryEntity")
    private Set<RecipeEntity> recipes;

}
