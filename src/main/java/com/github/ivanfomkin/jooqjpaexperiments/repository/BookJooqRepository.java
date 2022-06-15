package com.github.ivanfomkin.jooqjpaexperiments.repository;

import com.github.ivanfomkin.jooqjpaexperiments.entity.BookEntity;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

@Repository
public class BookJooqRepository {
    private final EntityManager entityManager;

    public BookJooqRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<BookEntity> executeAllBooks() {

        var tableName = getTableName(entityManager, BookEntity.class);
        org.jooq.Query jooqQuery = DSL.select().from(tableName);

        // Extract the SQL statement from the jOOQ query:
        Query result = entityManager.createNativeQuery(jooqQuery.getSQL(), BookEntity.class);

        // Extract the bind values from the jOOQ query:
        List<Object> values = jooqQuery.getBindValues();
        for (int i = 0; i < values.size(); i++) {
            result.setParameter(i + 1, values.get(i));
        }

        // There's an unsafe cast here, but we can be sure that we'll get the right type from JPA
        return result.getResultList();
    }

    /**
     * Returns the table name for a given entity type in the {@link EntityManager}.
     *
     * @param em          entity manager
     * @param entityClass target class
     * @return table name for entity
     */
    /* Resource: https://stackoverflow.com/a/26420307/16644196 */
    private <T> String getTableName(EntityManager em, Class<T> entityClass) {
        /*
         * Check if the specified class is present in the metamodel.
         * Throws IllegalArgumentException if not.
         */
        Metamodel meta = em.getMetamodel();
        EntityType<T> entityType = meta.entity(entityClass);

        //Check whether @Table annotation is present on the class.
        Table t = entityClass.getAnnotation(Table.class);

        return (t == null)
                ? entityType.getName().toUpperCase()
                : t.name();
    }

}
