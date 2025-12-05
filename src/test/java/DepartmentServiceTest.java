package tn.esprit.studentmanagement.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import tn.esprit.studentmanagement.entities.Department;

@DataJpaTest
@Import(DepartmentService.class) // Import your service since @DataJpaTest only loads repositories
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    void testSaveGetAndDeleteDepartment() {
        // Create a new Department
        Department dept = new Department();
        dept.setName("Computer Science");
        dept.setLocation("Building A");
        dept.setPhone("12345678");
        dept.setHead("Dr. Smith");

        // Save department
        Department saved = departmentService.saveDepartment(dept);
        assertNotNull(saved.getIdDepartment(), "Department ID should not be null");

        // Get by ID
        Department fetched = departmentService.getDepartmentById(saved.getIdDepartment());
        assertEquals("Computer Science", fetched.getName());

        // Get all
        List<Department> allDepts = departmentService.getAllDepartments();
        assertTrue(allDepts.size() > 0);

        // Delete department
        departmentService.deleteDepartment(saved.getIdDepartment());
        assertNull(departmentService.getDepartmentById(saved.getIdDepartment()));
    }
}
