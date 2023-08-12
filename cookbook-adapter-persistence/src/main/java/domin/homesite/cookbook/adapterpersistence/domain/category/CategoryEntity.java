package domin.homesite.cookbook.adapterpersistence.domain.category;

import domin.homesite.cookbook.adapterpersistence.domain.recipe.RecipeEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "CATEGORIES")
@Data
public class CategoryEntity {

    @Id
    @Column(name = "CATEGORY_OID")
    private String category_oid;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @JoinColumn(name = "RECIPE_OID")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "categoryEntity")
    private List<RecipeEntity> recipes;

}
