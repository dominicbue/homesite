package domin.homesite.cookbook.adapterpersistence.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENTS")
@Data
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJECT_OID")
    private String object_Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "UNIT")
    private String unit;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_OID", referencedColumnName = "OBJECT_OID", insertable = false, updatable = false)
    private RecipeEntity recipe;
}
