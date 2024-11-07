package ir.traning.repo;

import ir.traning.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TeacherRepository implements BaseEntityRepository<Teacher>{

    private final EntityManagerFactory entityManagerFactory;

    public TeacherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public void create(Teacher entity) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new SQLException("ERR CREATING Teacher!!:" + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Teacher entity) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new SQLException("ERR CREATING Teacher!!: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long Id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Teacher teacher = entityManager.find(Teacher.class, Id);
            if (teacher != null) {
                entityManager.remove(teacher);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new SQLException("ERR CREATING Teacher!!: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Teacher> findById(Long Id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Teacher teacher = null;

        try {
            teacher = entityManager.find(Teacher.class, Id);
        } catch (Exception e) {
            throw new SQLException("ERR CREATING EXAM!!: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(teacher);
    }

    @Override
    public List<Teacher> findAll() throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Teacher> teachers;
        try {
            TypedQuery<Teacher> query = entityManager.createQuery("SELECT s FROM Teacher s", Teacher.class);
            teachers = query.getResultList();
        } catch (Exception e) {
            throw new SQLException("ERR CREATING EXAM!!: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return teachers;
    }
}
