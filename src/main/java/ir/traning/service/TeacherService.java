package ir.traning.service;

import ir.traning.model.Teacher;
import ir.traning.repo.TeacherRepository;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(EntityManagerFactory entityManagerFactory) {
        this.teacherRepository = new TeacherRepository(entityManagerFactory);
    }

    public void addTeachers() throws SQLException {
        teacherRepository.create(new Teacher());
        teacherRepository.create(new Teacher());
    }

    public void deleteTeacher(Long id) throws SQLException {
        teacherRepository.delete(id);
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        teacherRepository.update(teacher);
    }

    public Optional<Teacher> findTeacherById(Long id) throws SQLException {
        return teacherRepository.findById(id);
    }

    public List<Teacher> findAllTeachers() throws SQLException {
        return teacherRepository.findAll();
    }
}
