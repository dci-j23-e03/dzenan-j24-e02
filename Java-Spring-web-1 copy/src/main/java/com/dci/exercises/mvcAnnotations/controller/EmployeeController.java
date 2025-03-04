package com.dci.exercises.mvcAnnotations.controller;

import com.dci.exercises.mvcAnnotations.domain.Employee;
import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final Set<Employee> employees = new HashSet<>();

  @PostMapping
  public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
    if (employees.add(employee)) {
      URI location = ServletUriComponentsBuilder
          .fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(employee.getId())
          .toUri();
      return ResponseEntity.created(location).build();
//          new ResponseEntity<>(employee, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @GetMapping
  public ResponseEntity<Set<Employee>> getEmployees(@RequestParam(required = false) Integer limit) {
    return new ResponseEntity<>(employees
        .stream()
        .limit(Optional.ofNullable(limit).orElse(employees.size()))
        .collect(Collectors.toSet()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
    Employee employee = employees
        .stream()
        .filter(e -> e.getId().equals(id))
        .findAny()
        .orElse(null);

    if (employee == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return ResponseEntity.ok(employee);
//          ResponseEntity.ok().body(employee);
//          new ResponseEntity<>(employee, HttpStatus.OK);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEmployee(@PathVariable String id) {
    employees.removeIf(employee -> employee.getId().equals(id));
  }
}
