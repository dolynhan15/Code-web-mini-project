package com.example.demoweb.service.impl;

import com.example.demoweb.constant.RequiredMessage;
import com.example.demoweb.exception.ResourceException;
import com.example.demoweb.model.Employee;
import com.example.demoweb.repository.EmployeeReposity;
import com.example.demoweb.service.EmployeeService;
import com.example.demoweb.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Transactional
@Service
public class EmployeeServiceImpl implements IService<Employee> {
    @Autowired
    private EmployeeReposity employeeReposity;

    public Boolean isFindEmployeeByEmail(String email) {
        Integer countEmployeeByEmail = employeeReposity.findByEmailId(email);
        System.out.println("employee count"+ countEmployeeByEmail);
        if (countEmployeeByEmail>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> findAll() {
        return employeeReposity.findAll();
    }

    @Override
    public Employee createOne(Employee employee) {
        Boolean isFindEmailOfEmployee = this.isFindEmployeeByEmail(employee.getEmailId());
        if (!isFindEmailOfEmployee) {
            return employeeReposity.save(employee);
        }
        throw new ResourceException(RequiredMessage.EMAIL_EXIST);
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceException(RequiredMessage.EMPLOYEE_NOT_EXISTS + " : " + id));
        return employee;
    }

    @Override
    public Employee updateOne(Long id, Employee employeeDetails) {
        Boolean isFindEmailOfEmployee = this.isFindEmployeeByEmail(employeeDetails.getEmailId());
        if (!isFindEmailOfEmployee) {
            Employee employee = employeeReposity.findById(id)
                    .orElseThrow(() -> new ResourceException(RequiredMessage.EMPLOYEE_NOT_EXISTS + " : " + id));

            employee.setFirstname(employeeDetails.getFirstname());
            employee.setLastname(employeeDetails.getLastname());
            employee.setEmailId(employeeDetails.getEmailId());
            Employee updatedEmployee = employeeReposity.save(employee);
            return updatedEmployee;
        }
        throw new ResourceException(RequiredMessage.EMAIL_EXIST);
    }

    @Override
    public void deleteById(Long id) {
        if (Objects.isNull(id)) throw new ResourceException(RequiredMessage.REQUIRED_ID);
        Employee employee = employeeReposity.findById(id)
                .orElseThrow(() -> new ResourceException(RequiredMessage.EMPLOYEE_NOT_EXISTS + " : " + id));
        employeeReposity.delete(employee);
    }
}

