package com.example.homework28emp;

import com.example.homework28emp.exception.EmployeeAlreadyAddedException;
import com.example.homework28emp.exception.EmployeeNotFoundException;
import com.example.homework28emp.exception.EmpoyeeStorageFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final Map<String, Employee> employees = new HashMap<>();
    private static final int LIMIT = 10;
    private String getKey(String firstName, String lastName){
        return firstName + " " + lastName;
    }

    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(key, employee);
            return employee;
        }
        throw new EmpoyeeStorageFullException();
    }
    public Employee deleteEmployee (String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee findEmployee (String firstName, String lastName){
        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public List<Employee> printEmployees () {
        return new ArrayList<>(employees.values());
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees.values());
    }
}