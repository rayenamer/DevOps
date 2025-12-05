package tn.esprit.studentmanagement.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.entities.Status;

@DataJpaTest
@Import({EnrollmentService.class, DepartmentService.class})
class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private DepartmentService departmentService;

    @Test
    void testSaveGetAndDeleteEnrollment() {
        // Create and save Department
        Department dept = new Department();
        dept.setName("Mathematics");
        dept = departmentService.saveDepartment(dept);

        // Create Enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setDepartment(dept);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setGrade(95.0);
        enrollment.setStatus(Status.ACTIVE);

        // Save Enrollment
        Enrollment saved = enrollmentService.saveEnrollment(enrollment);
        assertNotNull(saved.getIdEnrollment());

        // Get by ID
        Enrollment fetched = enrollmentService.getEnrollmentById(saved.getIdEnrollment());
        assertEquals(dept.getName(), fetched.getDepartment().getName());
        assertEquals(95.0, fetched.getGrade());

        // Get all
        List<Enrollment> allEnrollments = enrollmentService.getAllEnrollments();
        assertTrue(allEnrollments.size() > 0);

        // Delete Enrollment
        enrollmentService.deleteEnrollment(saved.getIdEnrollment());
        assertNull(enrollmentService.getEnrollmentById(saved.getIdEnrollment()));
    }
}
