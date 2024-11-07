package ir.traning.repo;

import ir.traning.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements BaseEntityRepository<Student> {

    private final EntityManagerFactory entityManagerFactory;

    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(Student entity) throws SQLException {
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
            throw new SQLException("Error creating student: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Student entity) throws SQLException {
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
            throw new SQLException("Error updating student: " + e.getMessage(), e);
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
            Student student = entityManager.find(Student.class, Id);
            if (student != null) {
                entityManager.remove(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new SQLException("Error deleting student: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Student> findById(Long Id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = null;

        try {
            student = entityManager.find(Student.class, Id);
        } catch (Exception e) {
            throw new SQLException("Error finding student: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> findAll() throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Student> students;

        try {
            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
            students = query.getResultList();
        } catch (Exception e) {
            throw new SQLException("Error finding all students: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return students;
    }
}