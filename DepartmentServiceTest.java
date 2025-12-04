package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department());
        departments.add(new Department());

        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(2, result.size());
    }

    @Test
    void getDepartmentById() {
        Department department = new Department();
        department.setIdDepartment(1L);
        department.setName("Test Department");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals("Test Department", result.getName());
    }

    @Test
    void saveDepartment() {
        Department department = new Department();
        department.setName("New Department");

        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.saveDepartment(department);

        assertNotNull(result);
        assertEquals("New Department", result.getName());
    }

    @Test
    void deleteDepartment() {
        departmentService.deleteDepartment(1L);
    }
}
