package domin.homesite.cookbook.adapterpersistence.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "INSTRUCTIONS")
@Data
public class InstructionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJECT_OID")
    private String object_Id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_OID", referencedColumnName = "OBJECT_OID", insertable = false, updatable = false)
    private RecipeEntity recipe;
}
