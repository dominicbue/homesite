package domin.homesite.cookbook.adapterpersistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.xml.sax.InputSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static javax.persistence.Persistence.createEntityManagerFactory;

public abstract class AbstractRepositoryTest {

    private final static File DBUNIT_IMPORT_FILE_DIRECTORY = new File("src/test/resources/dbunit");
    protected static EntityManager em;

    @BeforeAll
    public static void init() { em = createEntityManager(); }

    protected static EntityManager createEntityManager() {
        EntityManagerFactory emf = createEntityManagerFactory("repositoryTest");
        return emf.createEntityManager();
    }

    @BeforeEach
    public void setupDataBase() throws Exception {
        importDbUnitFile("dbunit_clear_database.xml");
    }

    @AfterAll
    public static void afterClass() {em.close();}

    @AfterEach
    public void closeActiveTransactions() {
        if(em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.clear();
    }

    protected void importDbUnitFile(String fileName) throws SQLException, DatabaseUnitException, IOException {
        em.getTransaction().begin();
        try (Connection connectionEm = em.unwrap(java.sql.Connection.class)) {
            IDatabaseConnection databaseConnection = new DatabaseConnection(connectionEm);
            DatabaseConfig config = databaseConnection.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
            config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, "true");
            try(FileInputStream byteStream = new FileInputStream(new File(DBUNIT_IMPORT_FILE_DIRECTORY, fileName))) {
                FlatXmlDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(byteStream)));
                DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
                em.getTransaction().commit();
            }
        }

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    protected void transactionBegin() {
        em.getTransaction().begin();
    }

    protected void transactionCommit() {
        em.getTransaction().commit();
        em.clear();
    }

    protected void injectEntityManagerToClass(AbstractRepository<?> testee) {
        testee.setEntityManager(em);
    }
}
