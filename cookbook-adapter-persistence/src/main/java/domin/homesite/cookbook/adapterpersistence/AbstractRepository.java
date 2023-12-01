package domin.homesite.cookbook.adapterpersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Optional;


public abstract class AbstractRepository<E> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("recipemanagment");
    private EntityManager em = emf.createEntityManager();

    protected AbstractRepository() {}
    protected void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    protected void persist(E persistenceObject) {
        em.persist(persistenceObject);
    }

    protected Optional<E> find(Class<E> clazz, Object primaryKey) {
        return Optional.ofNullable(em.find(clazz,primaryKey));
    }

    protected <X> TypedQuery<X> createNamedQuery(String sql, Class<X> clazz) {
        return em.createNamedQuery(sql, clazz);
    }
}
