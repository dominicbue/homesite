package domin.homesite.cookbook.adapterpersistence.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "CATEGORIES")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJECT_OID")
    private String object_Id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "RECIPE_OID")
    @Column(name = "RECIPE_OID")
    private RecipeEntity recipe;

}
