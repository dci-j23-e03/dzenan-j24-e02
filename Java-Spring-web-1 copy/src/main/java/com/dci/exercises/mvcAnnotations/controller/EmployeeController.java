package com.dci.exercises.mvcAnnotations.controller;

import com.dci.exercises.mvcAnnotations.domain.Employee;
import com.dci.exercises.mvcAnnotations.dto.EmployeeReqDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();


    @PostMapping("/add")
    public Employee addEmployee(@RequestBody EmployeeReqDto requestEmployeeReqDto) {

        Employee employee = new Employee(requestEmployeeReqDto.getFirstName(), requestEmployeeReqDto.getLastName(), requestEmployeeReqDto.getAge(), requestEmployeeReqDto.getPosition());
        employees.add(employee);
        System.out.println("Employee added: " + employee);

        return employee;
    }


    // (RequestParam) URL: http://localhost:8080/employees?limit=2
    @GetMapping
    public List<Employee> getEmployees(
            @RequestParam(required = false) Integer limit) {

        if (limit != null) {
            return employees.stream().limit(limit).collect(Collectors.toList());
        } else {
            return employees.stream().collect(Collectors.toList());
        }
    }

    // (Path variable) URL: http://localhost:8080/employees/1
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Integer employeeId) {
        return employees.stream().filter(employee -> employee.getId().equals(employeeId)).findFirst().orElse(null);
    }

    // (Path variable) URL: http://localhost:8080/employees/1
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employees.remove(employeeId);
        System.out.println("Employee with id " + employeeId + " deleted.");
    }

}
