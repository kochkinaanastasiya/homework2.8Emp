package com.example.homework28emp;

import com.example.homework28emp.exception.EmployeeAlreadyAddedException;
import com.example.homework28emp.exception.EmployeeNotFoundException;
import com.example.homework28emp.exception.EmployeeStorageFullException;
import com.example.homework28emp.service.EmployeeService;
import com.example.homework28emp.service.EmployeeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.example.homework28emp.TestConstants.*;

class EmployeeServiceImplTest {

    EmployeeService out = new EmployeeServiceImpl();

    @AfterEach
    public void afterEach(){
        out.getAll().forEach(employee -> out.deleteEmployee(employee.getFirstName(), employee.getLastName()));
    }

    @Test
    public void addEmployeeTest() {
        Employee result = out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1);
        Assertions.assertEquals(result, EMPLOYEE1);

        result = out.addEmployee(FIRST_NAME2, LAST_NAME2, DEPARTMENT2, SALARY2);
        Assertions.assertEquals(result, EMPLOYEE2);
    }

    @Test
    public void deleteEmployeeTest() {
        out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1);
        Employee result = out.deleteEmployee(FIRST_NAME1, LAST_NAME1);
        Assertions.assertEquals(result, EMPLOYEE1);

        out.addEmployee(FIRST_NAME2, LAST_NAME2, DEPARTMENT2, SALARY2);
        result = out.deleteEmployee(FIRST_NAME2, LAST_NAME2);
        Assertions.assertEquals(result, EMPLOYEE2);
    }

    @Test
    public void findEmployeeTest() {
        out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1);
        Employee result = out.findEmployee(FIRST_NAME1, LAST_NAME1);
        Assertions.assertEquals(result, EMPLOYEE1);

        out.addEmployee(FIRST_NAME2, LAST_NAME2, DEPARTMENT2, SALARY2);
        result = out.findEmployee(FIRST_NAME2, LAST_NAME2);
        Assertions.assertEquals(result, EMPLOYEE2);
    }

    @Test
    public void getAllTest() {
        out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1);
        out.addEmployee(FIRST_NAME2, LAST_NAME2, DEPARTMENT2, SALARY2);
        List<Employee> result = out.getAll();
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(EMPLOYEE1));
        Assertions.assertTrue(result.contains(EMPLOYEE2));
    }

    @Test
    public void EmployeeAlreadyAddedExceptionTest(){
        out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1));
    }

    @Test
    public void EmployeeStorageFullException(){
        for (int i = 0; i < 10; i++) {
            out.addEmployee(FIRST_NAME1 + i, LAST_NAME1, DEPARTMENT1, SALARY1);
        }
        Assertions.assertThrows(EmployeeStorageFullException.class,
                () -> out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1));

    }

    @Test
    public void EmployeeNotFoundException(){
        out.addEmployee(FIRST_NAME1, LAST_NAME1, DEPARTMENT1, SALARY1);
        out.deleteEmployee(FIRST_NAME1, LAST_NAME1);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.deleteEmployee(FIRST_NAME1, LAST_NAME1));
    }
}