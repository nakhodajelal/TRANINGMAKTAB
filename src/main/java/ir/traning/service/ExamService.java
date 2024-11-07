package ir.traning.service;

import ir.traning.model.Exam;
import ir.traning.repo.ExamRepository;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(EntityManagerFactory entityManagerFactory) {
        this.examRepository = new ExamRepository(entityManagerFactory);
    }

    public void addExams() throws SQLException {
        examRepository.create(new Exam());
        examRepository.create(new Exam());

    }

    public void deleteExam(Long id) throws SQLException {
        examRepository.delete(id);
    }

    public void updateExam(Exam exam) throws SQLException {
        examRepository.update(exam);
    }

    public Optional<Exam> findExamById(Long id) throws SQLException {
        return examRepository.findById(id);
    }

    public List<Exam> findAllExams() throws SQLException {
        return examRepository.findAll();
    }
}