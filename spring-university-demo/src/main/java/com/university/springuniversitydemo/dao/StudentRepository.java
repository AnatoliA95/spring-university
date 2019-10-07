package com.university.springuniversitydemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.springuniversitydemo.entity.Student;
import com.university.springuniversitydemo.entity.User;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findByUser(User user);
	
}
