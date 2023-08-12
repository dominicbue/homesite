package domin.homesite.cookbook.adapterpersistence.testutil;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EntityManagerProducer {

    @PersistenceContext(name="test")
    private EntityManager em;

    @Produces
    public EntityManager entityManager(){
        return em;
    }

}
