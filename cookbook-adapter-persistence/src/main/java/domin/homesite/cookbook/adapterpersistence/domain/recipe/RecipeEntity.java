package domin.homesite.cookbook.adapterpersistence.domain.recipe;

import domin.homesite.cookbook.adapterpersistence.domain.category.CategoryEntity;
import domin.homesite.cookbook.adapterpersistence.domain.ingredients.IngredientEntity;
import domin.homesite.cookbook.adapterpersistence.domain.instructions.InstructionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NamedQuery(name = "TEST", query = "SELECT r FROM RecipeEntity r")
@Table(name = "RECIPES")
@IdClass(RecipeEntityId.class)
public class RecipeEntity {

    @Id
    @Column(name = "OBJECT_OID")
    private String object_Id;
    @Column(name = "RECIPE_NAME")
    private String name;
    @Lob
    @Column(name = "PICTURE", length = 100000)
    private byte[] picture;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_OID", referencedColumnName = "OBJECT_OID", insertable = false, updatable = false)
    private CategoryEntity categoryEntity = new CategoryEntity();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "recipe")
    @Column(name = "INGREDIENT_OID")
    private List<IngredientEntity> ingredientEntities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "recipe")
    @Column(name = "INSTRUCTION_OID")
    private List<InstructionEntity> instructionEntities  = new ArrayList<>();

}
