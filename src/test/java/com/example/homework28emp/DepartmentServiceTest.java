package com.example.homework28emp;

import com.example.homework28emp.exception.EmployeeNotFoundException;
import com.example.homework28emp.service.DepartmentService;
import com.example.homework28emp.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach(){
        List<Employee> employees = List.of(
                new Employee("Алексей", "Алексеев", 1, 50000),
                new Employee("Александр", "Александров", 1, 70000),
                new Employee("Мария", "Кузнецова", 2, 60000),
                new Employee("Евгения", "Смолякова", 2, 40000)
                );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void getEmployeeWithMaxSalaryPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.getEmployeeWithMaxSalary(departmentId)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> departmentService.getEmployeeWithMaxSalary(3));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void getEmployeeWithMinSalaryPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.getEmployeeWithMinSalary(departmentId)).isEqualTo(expected);
    }

    @Test
    public void getEmployeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> departmentService.getEmployeeWithMinSalary(3));
    }

    @ParameterizedTest
    @MethodSource("employeesInDepartmentParams")
    public void getEmployeesInDepartmentTest(int departmentId, List<Employee> expected) {
        assertThat(departmentService.getEmployeesInDepartment(departmentId)).containsExactlyElementsOf(expected);
    }

    @Test
    public void getEmployeesByDepartmentsTest() {
        assertThat(departmentService.getEmployeesByDepartments()).containsAllEntriesOf(
                Map.of(
                        1, List.of(
                                new Employee("Алексей", "Алексеев", 1, 50000),
                                new Employee("Александр", "Александров", 1, 70000)),
                        2, List.of(
                                new Employee("Мария", "Кузнецова", 2, 60000),
                                new Employee("Евгения", "Смолякова", 2, 40000))
                )
        );
    }

    public static Stream<Arguments> employeeWithMaxSalaryParams(){
        return Stream.of(
                Arguments.of(1, new Employee("Александр", "Александров", 1, 70000)),
                Arguments.of(2, new Employee("Мария", "Кузнецова", 2, 60000))
        );
    }

    public static Stream<Arguments> employeeWithMinSalaryParams(){
        return Stream.of(
                Arguments.of(1, new Employee("Алексей", "Алексеев", 1, 50000)),
                Arguments.of(2, new Employee("Евгения", "Смолякова", 2, 40000))
        );
    }

    public static Stream<Arguments> employeesInDepartmentParams(){
        return Stream.of(
                Arguments.of(1, List.of(
                        new Employee("Алексей", "Алексеев", 1, 50000),
                        new Employee("Александр", "Александров", 1, 70000))),
                Arguments.of(2, List.of(
                        new Employee("Мария", "Кузнецова", 2, 60000),
                        new Employee("Евгения", "Смолякова", 2, 40000))),
                Arguments.of(3, Collections.emptyList())
        );
    }
}
