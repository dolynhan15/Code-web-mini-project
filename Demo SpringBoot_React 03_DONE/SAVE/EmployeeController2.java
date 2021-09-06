package com.example.demoweb.controller;

import com.example.demoweb.exception.GlobalExceptionHandler;
import com.example.demoweb.exception.ResourceNotFoundException;
import com.example.demoweb.model.Employee;
import com.example.demoweb.repository.EmployeeReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController2 {

    @Autowired
    private EmployeeReposity employeeReposity;

    //get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeReposity.findAll();
    }

    //create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            new GlobalExceptionHandler();
        return employeeReposity.save(employee);
    }
    //get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : "+id));
        return ResponseEntity.ok(employee);
    }
    //update employee by id rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : "+id));

        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname(employeeDetails.getLastname());
        employee.setEmailId(employeeDetails.getEmailId());
        Employee updatedEmployee = employeeReposity.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : "+ id));
        employeeReposity.delete(employee);
        return ResponseEntity.ok("deleted");
    }
}
