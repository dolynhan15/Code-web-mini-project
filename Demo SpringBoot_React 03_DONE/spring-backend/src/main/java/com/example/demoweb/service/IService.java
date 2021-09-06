package com.example.demoweb.service;

import com.example.demoweb.model.Employee;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T createOne(T item);
    T findById(Long id);
    T updateOne(Long id, T itemDetails);
    void deleteById(Long id);
}

