package com.example.homework28emp.controller;

import com.example.homework28emp.DepartmentService;
import com.example.homework28emp.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(
            @RequestParam("departmentId") int department){
        return departmentService.getEmployeeWithMaxSalary(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee getEmployeeWithMinSalary(
            @RequestParam("departmentId") int department) {
        return departmentService.getEmployeeWithMinSalary(department);
    }

    @GetMapping(path = "/all", params = "departmentId")
    public List<Employee> getEmployeesInDepartment(
            @RequestParam("departmentId") int department) {
        return departmentService.getEmployeesInDepartment(department);
    }

    @GetMapping(path = "/departments/all")
    public Map<Integer,List<Employee>> getEmployeesByDepartments() {
        return departmentService.getEmployeesByDepartments();
    }
}
