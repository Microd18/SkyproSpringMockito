package com.example.streamapi1.controllers;

import com.example.streamapi1.model.Employee;
import com.example.streamapi1.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> findEmployeesFromDepartment(@PathVariable() int id) {
        return departmentService.findArrayDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public double getSumSalaryInDepartment(@PathVariable() int id) {
        return departmentService.getSumSalaryInDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public Employee findEmployeeWithMaxSalaryFromDepartment(
            @PathVariable() int id) {
        return departmentService.getMaxSalaryInDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public Employee findEmployeeWithMinSalaryFromDepartment(
            @PathVariable() int id) {
        return departmentService.getMinSalaryInDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> findEmployees() {
        return departmentService.findEmployeesByDepartment();
    }


}
