package com.example.streamapi1.services;

import com.example.streamapi1.Exceptions.InvalidInputException;
import com.example.streamapi1.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;

    List<Employee> employeeList;

    public static Stream<Arguments> getMaxSalaryInDepartmentParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Артем", "Фролов", 1, 120000)),
                Arguments.of(2, new Employee("Андрей", "Петров", 2, 220000)),
                Arguments.of(3, new Employee("Максим", "Марков", 3, 320000))
        );
    }

    public static Stream<Arguments> getMinSalaryInDepartmentParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Ольга", "Шарова", 1, 20000)),
                Arguments.of(2, new Employee("Анна", "Каренина", 2, 21000)),
                Arguments.of(3, new Employee("Максим", "Марков", 3, 320000))
        );
    }

    public static Stream<Arguments> findArrayDepartmentParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Артем", "Фролов", 1, 120000),
                                new Employee("Ольга", "Шарова", 1, 20000)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Андрей", "Петров", 2, 220000),
                                new Employee("Анна", "Каренина", 2, 21000)
                        )
                ),
                Arguments.of(
                        3,
                        List.of(
                                new Employee("Максим", "Марков", 3, 320000)
                        )
                ),
                Arguments.of(
                        4,
                        Collections.emptyList()
                )
        );
    }

    public static Stream<Arguments> getSumSalaryInDepartmentParams() {
        return Stream.of(
                Arguments.of(
                        1, 140000.00
                ),
                Arguments.of(
                        2, 241000.00
                ),
                Arguments.of(
                        3, 320000.00
                )
        );
    }

    @BeforeEach
    public void setUp() {
        employeeList = List.of(
                new Employee("Артем", "Фролов", 1, 120000),
                new Employee("Андрей", "Петров", 2, 220000),
                new Employee("Максим", "Марков", 3, 320000),
                new Employee("Ольга", "Шарова", 1, 20000),
                new Employee("Анна", "Каренина", 2, 21000)
        );
        Mockito.when(employeeService.getAll()).thenReturn(employeeList);
        departmentService = new DepartmentService(employeeService);
    }

    @ParameterizedTest
    @MethodSource("getMaxSalaryInDepartmentParams")
    public void getMaxSalaryInDepartmentTest(int departmentId, Employee expected) {
        Assertions.assertThat(departmentService.getMaxSalaryInDepartment(departmentId)).isEqualTo(expected);
    }

    @Test
    public void getMaxSalaryInDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> departmentService.getMaxSalaryInDepartment(5));
    }

    @ParameterizedTest
    @MethodSource("getMinSalaryInDepartmentParams")
    public void getMinSalaryInDepartmentTest(int departmentId, Employee expected) {
        Assertions.assertThat(departmentService.getMinSalaryInDepartment(departmentId)).isEqualTo(expected);
    }

    @Test
    public void getMinSalaryInDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> departmentService.getMinSalaryInDepartment(5));
    }

    @ParameterizedTest
    @MethodSource("findArrayDepartmentParams")
    public void findArrayDepartmentTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentService.findArrayDepartment(departmentId)).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void findEmployeesGroupedByDepartment() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Артем", "Фролов", 1, 120000),
                        new Employee("Ольга", "Шарова", 1, 20000)
                ),
                2,
                List.of(
                        new Employee("Андрей", "Петров", 2, 220000),
                        new Employee("Анна", "Каренина", 2, 21000)
                ),
                3,
                List.of(
                        new Employee("Максим", "Марков", 3, 320000)
                )
        );

        Assertions.assertThat(departmentService.findEmployeesByDepartment()).containsExactlyInAnyOrderEntriesOf(expected);
    }

    @ParameterizedTest
    @MethodSource("getSumSalaryInDepartmentParams")
    public void getSumSalaryInDepartmentTest(int departmentId, double expected) {
        Assertions.assertThat(departmentService.getSumSalaryInDepartment(departmentId)).isEqualTo(expected);
    }

}