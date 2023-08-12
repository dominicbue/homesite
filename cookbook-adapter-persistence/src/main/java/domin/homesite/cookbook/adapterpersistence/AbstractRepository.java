package domin.homesite.cookbook.adapterpersistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;


public abstract class AbstractRepository<E> {

    @PersistenceContext(name = "recipemanagment")
    private EntityManager em;

    protected AbstractRepository() {}
    protected void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    protected void persist(E persistenceObject, Boolean updateEntity) {
        if(updateEntity) {
            em.merge(persistenceObject);
        }
        em.persist(persistenceObject);
    }

    protected Optional<E> find(Class<E> clazz, Object primaryKey) {
        return Optional.ofNullable(em.find(clazz,primaryKey));
    }

    protected <X> TypedQuery<X> createNamedQuery(String sql, Class<X> clazz) {
        return em.createNamedQuery(sql, clazz);
    }
}
