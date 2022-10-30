package com.example.homework28emp;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int department, double salary);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);
    List <Employee> getAll();
    List<Employee> printEmployees();

}
