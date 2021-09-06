package com.example.demoweb.repository;

import com.example.demoweb.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReposity extends JpaRepository<Employee,Long> {
    @Query("SELECT count(e) FROM Employee e WHERE e.emailId = :Email")
    Integer findByEmailId(String Email);

}
