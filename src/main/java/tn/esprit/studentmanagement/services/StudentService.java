package tn.esprit.studentmanagement.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    private static final Logger logger = LogManager.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        logger.info("Fetching all students from database");
        List<Student> students = studentRepository.findAll();
        logger.debug("Found {} students", students.size());
        return students;
    }

    public Student getStudentById(Long id) {
        logger.info("Fetching student with id: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            logger.warn("Student with id {} not found", id);
        } else {
            logger.debug("Found student: {} {}", student.getFirstName(), student.getLastName());
        }
        return student;
    }

    public Student saveStudent(Student student) {
        logger.info("Saving new student: {} {}", student.getFirstName(), student.getLastName());
        try {
            Student savedStudent = studentRepository.save(student);
            logger.info("Student saved successfully with id: {}", savedStudent.getId());
            return savedStudent;
        } catch (Exception e) {
            logger.error("Error saving student: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteStudent(Long id) {
        logger.info("Deleting student with id: {}", id);
        try {
            studentRepository.deleteById(id);
            logger.info("Student with id {} deleted successfully", id);
        } catch (Exception e) {
            logger.error("Error deleting student with id {}: {}", id, e.getMessage());
            throw e;
        }
    }
}
