package com.example.demoweb.repository;

import com.example.demoweb.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReposity extends JpaRepository<Employee,Long> {
}
