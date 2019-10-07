package com.university.springuniversitydemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.university.springuniversitydemo.entity.University;
@RepositoryRestResource(path="university")
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
