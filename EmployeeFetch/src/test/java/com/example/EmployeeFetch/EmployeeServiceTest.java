package com.example.EmployeeFetch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Map;
import java.util.Set;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Mock
    private Map<String, Employee> mockMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService();
        employeeService.map = mockMap;
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee(1, "John", 50000);
        when(mockMap.containsKey(employee.getName())).thenReturn(false);

        String result = employeeService.addemp(employee);

        assertEquals("New Employee added", result);
        verify(mockMap).put(employee.getName(), employee);
    }

    @Test
    void testAddEmployeeAlreadyExists() {
        Employee employee = new Employee(1, "John", 50000);
        when(mockMap.containsKey(employee.getName())).thenReturn(true);

        EmployeeAlreadyExist exception = assertThrows(EmployeeAlreadyExist.class,
                () -> employeeService.addemp(employee));

        assertEquals("employee with " + employee.getId() + "already exist", exception.getMessage());
        verify(mockMap, never()).put(anyString(), any(Employee.class));
    }

    @Test
    void testGetEmployee() {
        Employee employee1 = new Employee(1, "John", 50000);
        Employee employee2 = new Employee(2, "Alice", 60000);
        when(mockMap.entrySet()).thenReturn(Set.of(
                Map.entry("John", employee1),
                Map.entry("Alice", employee2)
        ));

        List<Employee> result = employeeService.getEmployee(40000, 55000);

        assertEquals(1, result.size());
        assertTrue(result.contains(employee1));
    }

    // Similar tests can be written for other methods
}

