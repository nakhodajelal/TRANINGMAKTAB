package ir.traning.service;

import ir.traning.model.Student;
import ir.traning.repo.StudentRepository;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(EntityManagerFactory entityManagerFactory) {
        this.studentRepository = new StudentRepository(entityManagerFactory);
    }

    public void addStudents() throws SQLException {
        studentRepository.create(new Student());
        studentRepository.create(new Student());


    }

    public Optional<Student> findStudentById(Long id) throws SQLException {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id) throws SQLException {
        studentRepository.delete(id);
    }

    public void updateStudent(Student student) throws SQLException {
        studentRepository.update(student);
    }

    public List<Student> findAllStudents() throws SQLException {
        return studentRepository.findAll();
    }
}