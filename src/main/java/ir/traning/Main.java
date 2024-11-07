package ir.traning;

import ir.traning.model.Course;
import ir.traning.model.Exam;
import ir.traning.model.Student;
import ir.traning.model.Teacher;
import ir.traning.service.CourseService;
import ir.traning.service.ExamService;
import ir.traning.service.StudentService;
import ir.traning.service.TeacherService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbc-postgres");


        StudentService studentService = new StudentService(entityManagerFactory);
        CourseService courseService = new CourseService(entityManagerFactory);
        ExamService examService = new ExamService(entityManagerFactory);
        TeacherService teacherService = new TeacherService(entityManagerFactory);


        try {
//             All add
            studentService.addStudents();
            courseService.addCourses();
            examService.addExams();
            teacherService.addTeachers();

            // find All
            List<Student> students = studentService.findAllStudents();
            System.out.println("All students:");
            students.forEach(student -> System.out.println("Student Name: " + student.getFirstName() + ", National code: " + student.getNationalCode()));
            List<Course> courses = courseService.findAllCourses();
            System.out.println("All courses:");
            courses.forEach(course -> System.out.println("Course title: " + course.getTitle()));
            List<Exam> exams = examService.findAllExams();
            System.out.println("All exams:");
            exams.forEach(exam -> System.out.println("Exam title: " + exam.getExamTitle()));
            List<Teacher> teachers = teacherService.findAllTeachers();
            System.out.println("find all teachers:");
            teachers.forEach(teacher -> System.out.println("teacher name:" + teacher.getFirstName()));

            //All find by ides
            Optional<Course> optionalCourse = courseService.findCourseById(1L);
            optionalCourse.ifPresent(course -> System.out.println("Found course: " + course.getTitle()));

            Optional<Teacher> optionalTeacher = teacherService.findTeacherById(1L);
            optionalTeacher.ifPresent(teacher -> System.out.println("teacher:" + teacher.getFirstName()));

            Optional<Student> optionalStudent = studentService.findStudentById(1L);
            optionalStudent.ifPresent(student -> System.out.println("fist name:" + student.getFirstName()));

            Optional<Exam> optionalExam = examService.findExamById(1L);
            optionalExam.ifPresent(exam -> System.out.println("exam title:" + exam.getExamTitle()));

            //update && delete
            //.
            //.
            //.

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            entityManagerFactory.close();
        }
    }
}