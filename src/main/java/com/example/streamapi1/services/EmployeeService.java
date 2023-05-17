package com.example.streamapi1.services;

import com.example.streamapi1.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private static final int SIZE = 10;
    private final List<Employee> employees = new ArrayList<>(SIZE);


    // Добавить нового сотрудника
    public Employee addEmployee(String name, String surname, int department, double salary) {
        Employee people1 = new Employee(name, surname, department, salary);
        if (employees.contains(people1)){
            throw new IllegalArgumentException("Такой сотрудник уже в базе");
        }

        if (employees.size() == SIZE) {
            throw new ArrayStoreException("Нет места для нового сотрудника");
        }
        employees.add(people1);

        return people1;
    }

    // Удалить сотрудника
    public Employee remove(String name, String surname, int department, double salary) {
        Employee employee = new Employee(name, surname, department, salary);
        if (!employees.contains(employee)) {
            throw new RuntimeException();
        }
        employees.remove(employee);
        return employee;
    }

    // Найти сотрудника
    public Employee find(String name, String surname, int department, double salary) {
        Employee employee = new Employee(name, surname, department, salary);
        if (!employees.contains(employee)) {
            throw new RuntimeException();
        }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeService that = (EmployeeService) o;
        return Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees);
    }
}
