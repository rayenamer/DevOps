package tn.esprit.studentmanagement.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentService.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments from database");
        List<Department> departments = departmentRepository.findAll();
        logger.debug("Found {} departments", departments.size());
        return departments;
    }

    @Override
    public Department getDepartmentById(Long idDepartment) {
        logger.info("Fetching department with id: {}", idDepartment);
        try {
            Department department = departmentRepository.findById(idDepartment).orElse(null);
            if (department == null) {
                logger.warn("Department with id {} not found", idDepartment);
            } else {
                logger.debug("Found department: {}", department.getName());
            }
            return department;
        } catch (Exception e) {
            logger.error("Error fetching department with id {}: {}", idDepartment, e.getMessage());
            throw e;
        }
    }

    @Override
    public Department saveDepartment(Department department) {
        logger.info("Saving department: {}", department.getName());
        try {
            Department savedDepartment = departmentRepository.save(department);
            logger.info("Department saved successfully with id: {}", savedDepartment.getIdDepartment());
            return savedDepartment;
        } catch (Exception e) {
            logger.error("Error saving department: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteDepartment(Long idDepartment) {
        logger.info("Deleting department with id: {}", idDepartment);
        try {
            departmentRepository.deleteById(idDepartment);
            logger.info("Department with id {} deleted successfully", idDepartment);
        } catch (Exception e) {
            logger.error("Error deleting department with id {}: {}", idDepartment, e.getMessage());
            throw e;
        }
    }
}
