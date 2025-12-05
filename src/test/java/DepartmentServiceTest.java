package tn.esprit.studentmanagement.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tn.esprit.studentmanagement.entities.Department;

@SpringBootTest
@ActiveProfiles("test")  // Uses H2 configuration
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    void testSaveAndGetDepartment() {
        Department dept = new Department();
        dept.setName("Computer Science");
        
        // Save department
        Department saved = departmentService.saveDepartment(dept);
        assertNotNull(saved.getIdDepartment(), "Department ID should not be null");

        // Fetch by ID
        Department fetched = departmentService.getDepartmentById(saved.getIdDepartment());
        assertEquals("Computer Science", fetched.getName());

        // Fetch all
        List<Department> allDepts = departmentService.getAllDepartments();
        assertTrue(allDepts.size() > 0);

        // Delete department
        departmentService.deleteDepartment(saved.getIdDepartment());
        assertNull(departmentService.getDepartmentById(saved.getIdDepartment()));
    }
}
