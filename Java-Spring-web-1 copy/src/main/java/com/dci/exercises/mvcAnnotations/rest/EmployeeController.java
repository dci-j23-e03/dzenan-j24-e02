package com.dci.exercises.mvcAnnotations.rest;

import com.dci.exercises.mvcAnnotations.domain.Employee;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private List<Employee> employees;
    private int idCounter = 1;

    @PostConstruct
    private void init() {
        employees = new ArrayList<>();

        for (; idCounter <= 5; idCounter++) {
            employees.add(new Employee(
                    Integer.toString(idCounter),
                    "John",
                    "Smith " + idCounter,
                    idCounter * 10,
                    "Position " + idCounter));
        }
    }

    // crud operations on employees

    // create
    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee) {

        employee = new Employee(
                Integer.toString(idCounter++),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(),
                employee.getPosition()
        );
        employees.add(employee);

        return employee;
    }

    // read
    @GetMapping("/")
    public List<Employee> retrieveEmployees(@RequestParam(required = false, defaultValue = "0") int limit) {
        
        if (limit <= 0) {
            return employees;
        }

        return employees.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    // read by id
    @GetMapping("/{id}")
    public Employee retrieveEmployee(@PathVariable String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // update
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {

        String idBody = employee.getId();
        if (idBody == null || idBody.isEmpty()) {
            employee = new Employee(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAge(),
                    employee.getPosition()
            );
        }

        if (!idBody.equals(id)) {
            return null;
        }

        if (employees.removeIf(e -> e.getId().equals(id))) {
            employees.add(employee);
            System.out.println(employee);
            return employee;
        }

        return null;
    }

    // delete
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

}
