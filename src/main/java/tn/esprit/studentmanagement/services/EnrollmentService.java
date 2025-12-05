package tn.esprit.studentmanagement.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;
import tn.esprit.studentmanagement.entities.Enrollment;

import java.util.List;

@Service
public class EnrollmentService implements IEnrollment {
    private static final Logger logger = LogManager.getLogger(EnrollmentService.class);
    
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Override
    public List<Enrollment> getAllEnrollments() {
        logger.info("Fetching all enrollments from database");
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        logger.debug("Found {} enrollments", enrollments.size());
        return enrollments;
    }

    @Override
    public Enrollment getEnrollmentById(Long idEnrollment) {
        logger.info("Fetching enrollment with id: {}", idEnrollment);
        try {
            Enrollment enrollment = enrollmentRepository.findById(idEnrollment).orElse(null);
            if (enrollment == null) {
                logger.warn("Enrollment with id {} not found", idEnrollment);
            } else {
                logger.debug("Found enrollment with id: {}", enrollment.getIdEnrollment());
            }
            return enrollment;
        } catch (Exception e) {
            logger.error("Error fetching enrollment with id {}: {}", idEnrollment, e.getMessage());
            throw e;
        }
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        logger.info("Saving enrollment");
        try {
            Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
            logger.info("Enrollment saved successfully with id: {}", savedEnrollment.getIdEnrollment());
            return savedEnrollment;
        } catch (Exception e) {
            logger.error("Error saving enrollment: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteEnrollment(Long idEnrollment) {
        logger.info("Deleting enrollment with id: {}", idEnrollment);
        try {
            enrollmentRepository.deleteById(idEnrollment);
            logger.info("Enrollment with id {} deleted successfully", idEnrollment);
        } catch (Exception e) {
            logger.error("Error deleting enrollment with id {}: {}", idEnrollment, e.getMessage());
            throw e;
        }
    }
}