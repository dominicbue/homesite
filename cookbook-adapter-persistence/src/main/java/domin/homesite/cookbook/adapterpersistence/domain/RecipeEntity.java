package domin.homesite.cookbook.adapterpersistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NamedQuery(name = "TEST", query = "SELECT r FROM RecipeEntity r")
@Table(name = "RECIPES")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJECT_OID")
    private String object_Id;
    @Column(name = "RECIPE_NAME")
    private String name;
    @Lob
    @Column(name = "PICTURE", length = 100000)
    private byte[] picture;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_OID", referencedColumnName = "OBJECT_OID", insertable = false, updatable = false)
    private CategoryEntity categoryEntity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "recipe")
    @Column(name = "INGREDIENT_OID")
    private List<IngredientEntity> ingredientEntities;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "recipe")
    @Column(name = "INSTRUCTION_OID")
    private List<InstructionEntity> instructionEntities;

}
