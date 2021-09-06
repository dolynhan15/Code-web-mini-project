package com.example.demoweb.controller;

import com.example.demoweb.constant.URLConstant;
import com.example.demoweb.exception.ResourceNotFoundException;
import com.example.demoweb.model.Employee;
import com.example.demoweb.repository.EmployeeReposity;
import com.example.demoweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(URLConstant.WORK_PATH)
public class EmployeeController {

    @Autowired
    private EmployeeReposity employeeReposity;

    @Autowired
    private EmployeeService employeeService;

    //get all employees
    @GetMapping()
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //create employee rest api
    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee) {
//        return employeeService.createEmployee(employee, HttpStatus.CREATED);
        Employee employee1 = employeeService.createEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);


    }

    //get employee by id rest api
    @GetMapping(URLConstant.ID)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    //update employee by id rest api
    @PutMapping(URLConstant.ID)
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody @Valid Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping(URLConstant.ID)
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
