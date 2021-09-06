package com.example.demoweb.controller;

import com.example.demoweb.constant.URLConstant;
import com.example.demoweb.model.Employee;
import com.example.demoweb.repository.EmployeeReposity;
import com.example.demoweb.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = URLConstant.URL_FRONTEND)

@RestController
@RequestMapping(URLConstant.WORK_PATH)
public class EmployeeController {

    @Autowired
    private EmployeeReposity employeeReposity;

    @Autowired
    private IService<Employee> employeeService;

    //get all employees
    @GetMapping()
//    @CrossOrigin(origins = "http://localhost:3000")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    //create employee rest api
    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee) {
        Employee createdEmployee = employeeService.createOne(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    //get employee by id rest api
    @GetMapping(URLConstant.ID)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    //update employee by id rest api
    @PutMapping(URLConstant.ID)
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody @Valid Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateOne(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping(URLConstant.ID)
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
