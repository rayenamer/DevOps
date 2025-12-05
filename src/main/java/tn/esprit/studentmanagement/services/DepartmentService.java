package tn.esprit.studentmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Save or update a department
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get by ID, return null if not found
    public Department getDepartmentById(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        return optional.orElse(null);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Delete by ID, safe if ID does not exist
    public void deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
    }
}
