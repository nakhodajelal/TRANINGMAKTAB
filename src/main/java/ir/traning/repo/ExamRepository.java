package ir.traning.repo;

import ir.traning.model.Exam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExamRepository implements BaseEntityRepository<Exam> {

    private final EntityManagerFactory entityManagerFactory;

    public ExamRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(Exam entity) throws SQLException {
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
            throw new SQLException("ERR CREATING EXAM!!:" + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Exam entity) throws SQLException {
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
            throw new SQLException("ERR CREATING EXAM!!: " + e.getMessage(), e);
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
            Exam exam = entityManager.find(Exam.class, Id);
            if (exam != null) {
                entityManager.remove(exam);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new SQLException("ERR CREATING EXAM!!: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Exam> findById(Long Id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Exam exam = null;

        try {
            exam = entityManager.find(Exam.class, Id);
        } catch (Exception e) {
            throw new SQLException("ERR CREATING EXAM!!: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(exam);
    }

    @Override
    public List<Exam> findAll() throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Exam> exams;
        try {
            TypedQuery<Exam> query = entityManager.createQuery("SELECT s FROM Exam s", Exam.class);
            exams = query.getResultList();
        } catch (Exception e) {
            throw new SQLException("ERR CREATING EXAM!!: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return exams;
    }
}

