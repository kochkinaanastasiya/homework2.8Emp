package com.example.homework28emp;

import com.example.homework28emp.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentService(EmployeeServiceImpl employeeService){
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalary(int department){
        return employeeService.getAll().stream()
                .filter(employee-> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee getEmployeeWithMinSalary(int department){
        return employeeService.getAll().stream()
                .filter(employee-> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getEmployeesInDepartment(int department){
        return employeeService.getAll().stream()
                .filter(employee-> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer,List<Employee>> getEmployeesByDepartments(){
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }





















}
