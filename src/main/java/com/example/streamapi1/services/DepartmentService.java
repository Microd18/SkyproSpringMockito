package com.example.streamapi1.services;

import com.example.streamapi1.Exceptions.InvalidInputException;
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

    // #1 Напечатать всех сотрудников отдела
    public List<Employee> findArrayDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    // #2 Получить сумму затрат на зарплату по отделу
    public double getSumSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    // #3 Получить сотрудника с максимальной зарплатой в отделе
    public Employee getMaxSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(InvalidInputException::new);
    }

    // #4 Получить сотрудника с минимальной зарплатой в отделе
    public Employee getMinSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(InvalidInputException::new);
    }

    // #5
    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }


}
