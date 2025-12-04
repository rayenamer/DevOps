package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
    }

    @Test
    void getStudentById() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Test");
        student.setLastName("Student");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals("Test", result.getFirstName());
    }

    @Test
    void saveStudent() {
        Student student = new Student();
        student.setFirstName("New");
        student.setLastName("Student");

        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.saveStudent(student);

        assertNotNull(result);
        assertEquals("New", result.getFirstName());
    }

    @Test
    void deleteStudent() {
        // This is a void method, so we can only verify that it runs without errors
        studentService.deleteStudent(1L);
    }
}
