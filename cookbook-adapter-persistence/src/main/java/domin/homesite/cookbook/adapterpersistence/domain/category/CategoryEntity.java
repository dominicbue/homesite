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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_OID")
    private String category_Id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "categoryEntity")
    @Column(name = "RECIPE_OID")
    private List<RecipeEntity> recipes;

}
