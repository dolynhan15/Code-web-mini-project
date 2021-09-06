package com.example.demoweb.service.impl;

import com.example.demoweb.constant.RequiredMessage;
import com.example.demoweb.exception.ResourceNotFoundException;
import com.example.demoweb.model.Employee;
import com.example.demoweb.repository.EmployeeReposity;
import com.example.demoweb.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeReposity employeeReposity;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeReposity.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeReposity.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RequiredMessage.EMPLOYEE_NOT_EXISTS + " : " + id));
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        if (Objects.isNull(id)) throw new ResourceNotFoundException(RequiredMessage.REQUIRED_ID);
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RequiredMessage.EMPLOYEE_NOT_EXISTS + " : " + id));

        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname(employeeDetails.getLastname());
        employee.setEmailId(employeeDetails.getEmailId());
        Employee updatedEmployee = employeeReposity.save(employee);
        return updatedEmployee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (Objects.isNull(id)) throw new ResourceNotFoundException(RequiredMessage.REQUIRED_ID);
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RequiredMessage.EMPLOYEE_NOT_EXISTS + " : " + id));
        employeeReposity.delete(employee);
    }


}

