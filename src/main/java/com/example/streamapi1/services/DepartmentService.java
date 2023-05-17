package com.example.streamapi1.services;

import com.example.streamapi1.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Получить сотрудника с минимальной зарплатой в отделе
    public Employee getMinSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    // Получить сотрудника с максимальной зарплатой в отделе
    public Employee getMaxSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    // Напечатать всех сотрудников отдела
    public List<Employee> findArrayDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // Посчитать сумму затрат на зарплаты в месяц.
    public double getSalarySumInMonth(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getSalary)
                .mapToDouble(e -> e)
                .sum();
    }

    // Найти сотрудника с минимальной зарплатой.
    public Employee getMinSalary() {
        return employeeService.getAll().stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    // Найти сотрудника с максимальной зарплатой.
    public Employee getMaxSalary() {
        return employeeService.getAll().stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    // Подсчитать среднее значение зарплат
    public double getAverageSalary(List<Employee> employees) {
        return getSalarySumInMonth(employees) / employees.size();
    }

    // Изменить отдел
    public void editDepartmentEmployee(Employee employee, int department) {
        employeeService.getAll().stream()
                .filter(value -> Objects.equals(employee, value))
                .findFirst()
                .ifPresent(value -> value.setDepartment(department));
    }

    // Проиндексировать зарплату всех сотрудников отдела
    public void salaryIndexationInDepartment(int department, double percent) {
        employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> employee.setSalary(
                        (int) (employee.getSalary() + employee.getSalary() * percent / 100)
                ));
    }

    // Получить среднюю зарплату по отделу
    public double getAverageSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
    }

    // Получить сумму затрат на зарплату по отделу
    public double getSumSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }



}
