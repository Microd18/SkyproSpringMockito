package com.example.streamapi1.services;

import com.example.streamapi1.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTest {

    @Mock
    private EmployeeService service;

    private String NAME;
    private String SURNAME;
    private int DEPARTMENT;
    private double SALARY;

    private String NAME2;
    private String SURNAME2;
    private int DEPARTMENT2;
    private double SALARY2;

    @BeforeEach
    public void setUp() {
        service = new EmployeeService();

        NAME = "James";
        SURNAME = "Gosling";
        DEPARTMENT = 1;
        SALARY = 300000;

        NAME2 = "Nicola";
        SURNAME2 = "Tesla";
        DEPARTMENT2 = 4;
        SALARY2 = 70000;

    }

    @Test
    public void returnCorrectAddEmployee() {
        Employee expected = new Employee(NAME, SURNAME, DEPARTMENT, SALARY);
        Employee actual = service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY);

        assertEquals(expected, actual);

        expected = new Employee(NAME2, SURNAME2, DEPARTMENT2, SALARY2);
        actual = service.addEmployee(NAME2, SURNAME2, DEPARTMENT2, SALARY2);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenReAddingEmployee() {
        service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY);

        assertThrows(IllegalArgumentException.class, () -> service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY));
    }

    @Test
    public void shouldThrowArrayStoreExceptionWhenStoreIsFull() {
        service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY);
        service.addEmployee(NAME2, SURNAME, DEPARTMENT, SALARY);
        service.addEmployee(NAME, SURNAME2, DEPARTMENT, SALARY);
        service.addEmployee(NAME, SURNAME, DEPARTMENT2, SALARY);
        service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY2);
        service.addEmployee(NAME2, SURNAME2, DEPARTMENT, SALARY);
        service.addEmployee(NAME, SURNAME2, DEPARTMENT2, SALARY);
        service.addEmployee(NAME, SURNAME, DEPARTMENT2, SALARY2);
        service.addEmployee(NAME2, SURNAME2, DEPARTMENT2, SALARY);
        service.addEmployee(NAME, SURNAME2, DEPARTMENT2, SALARY2);

        assertThrows(ArrayStoreException.class, () -> service.addEmployee(NAME2, SURNAME2, DEPARTMENT2, SALARY2));
    }

    @Test
    public void returnOfRemovedEmployee() {
        service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY);
        Employee expected = new Employee(NAME, SURNAME, DEPARTMENT, SALARY);
        Employee actual = service.remove(NAME, SURNAME, DEPARTMENT, SALARY);

        assertEquals(expected, actual);

        service.addEmployee(NAME2, SURNAME2, DEPARTMENT2, SALARY2);
        expected = new Employee(NAME2, SURNAME2, DEPARTMENT2, SALARY2);
        actual = service.remove(NAME2, SURNAME2, DEPARTMENT2, SALARY2);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenRemovingNullEmployee() {
        assertThrows(RuntimeException.class, () -> service.remove(NAME, SURNAME, DEPARTMENT, SALARY));
    }

    @Test
    public void returnCorrectEmployeeWhenFound() {
        Employee expected = service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY);
        Employee actual = service.find(NAME, SURNAME, DEPARTMENT, SALARY);

        assertEquals(expected, actual);

        expected = service.addEmployee(NAME2, SURNAME2, DEPARTMENT2, SALARY2);
        actual = service.find(NAME2, SURNAME2, DEPARTMENT2, SALARY2);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenEmployeeNotFound() {
        assertThrows(RuntimeException.class, () -> service.find(NAME, SURNAME, DEPARTMENT, SALARY));
    }

    @Test
    public void getAllTest() {
        List<Employee> expected = new ArrayList<>();

        expected.add(new Employee(NAME, SURNAME, DEPARTMENT, SALARY));
        expected.add(new Employee(NAME2, SURNAME2, DEPARTMENT2, SALARY2));

        service.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY);
        service.addEmployee(NAME2, SURNAME2, DEPARTMENT2, SALARY2);

        List<Employee> actual = service.getAll();

        assertEquals(expected, actual);
    }

}