package com.example.homework28emp.controller;

import com.example.homework28emp.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.homework28emp.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("salary") double salary,
            @RequestParam("department") int department) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/delete")
    public Employee deleteEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/print")
    public Object printEmployees() {
        List<Employee> employees = null;
        try {
            employees = employeeService.printEmployees();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

}
