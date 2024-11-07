package ir.traning.repo;

import ir.traning.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseRepository implements BaseEntityRepository<Course>{

    private final EntityManagerFactory entityManagerFactory;

    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(Course entity) throws SQLException {
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
            throw new SQLException("Error creating course: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Course entity) throws SQLException {
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
            throw new SQLException("Error updating course: " + e.getMessage(), e);
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
            Course course = entityManager.find(Course.class, Id);
            if (course != null) {
                entityManager.remove(course);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new SQLException("Error deleting course: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Course> findById(Long Id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
       Course course = null;

        try {
            course = entityManager.find(Course.class, Id);
        } catch (Exception e) {
            throw new SQLException("Error finding course: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(course);
    }

    @Override
    public List<Course> findAll() throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Course> courses;

        try {
            TypedQuery<Course> query = entityManager.createQuery("SELECT s FROM Course s", Course.class);
            courses = query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error finding all COURSES: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return courses;
    }
}
