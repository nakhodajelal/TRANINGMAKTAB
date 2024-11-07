package ir.traning.service;
import ir.traning.model.Course;
import ir.traning.repo.CourseRepository;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(EntityManagerFactory entityManagerFactory) {
        this.courseRepository = new CourseRepository(entityManagerFactory);
    }

    public void addCourses() throws SQLException {
        courseRepository.create(new Course());
        courseRepository.create(new Course());

    }

    public void deleteCourse(Long id) throws SQLException {
        courseRepository.delete(id);
    }

    public void updateCourse(Course course) throws SQLException {
        courseRepository.update(course);
    }

    public Optional<Course> findCourseById(Long id) throws SQLException {
        return courseRepository.findById(id);
    }

    public List<Course> findAllCourses() throws SQLException {
        return courseRepository.findAll();
    }
}