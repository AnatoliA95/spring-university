package com.university.springuniversitydemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.springuniversitydemo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
