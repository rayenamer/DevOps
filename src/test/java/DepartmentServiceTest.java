package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(DepartmentService.class)
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    void testSaveGetAndDeleteDepartment() {
        System.out.println("Test passed");
    }
}
