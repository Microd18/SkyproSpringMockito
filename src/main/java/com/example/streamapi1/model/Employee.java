package com.example.streamapi1.model;

import com.example.streamapi1.Exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {

    private String name;
    private String surname;
    private int department;
    private double salary;
    private static int counter;
    private final int id;


    public Employee(String name, String surname, int department, double salary) {
        counter++;
        id = counter;
        setName(name.toLowerCase());
        setSurname(surname.toLowerCase());
        setDepartment(department);
        setSalary(salary);
    }

    public void setDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Неверный номер отдела");
        }
        this.department = department;
    }

    public void setName(String name){
        if (!StringUtils.isAlpha(name)){
            throw new InvalidInputException("йоу");
        }

        name = StringUtils.capitalize(name);

        this.name = name;
    }

    public void setSurname(String surname) {
        if (!StringUtils.isAlpha(surname)){
            throw new InvalidInputException("йоу");
        }

        surname = StringUtils.capitalize(surname);

        this.surname = surname;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("ФИ: %s, отдел: %d, ЗП: %f", getFullName(), department, salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) == 0 && name.equals(employee.name) && surname.equals(employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, department, salary);
    }
}
