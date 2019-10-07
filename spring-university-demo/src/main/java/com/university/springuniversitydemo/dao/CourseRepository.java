package com.university.springuniversitydemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.university.springuniversitydemo.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query("Select course from Course course inner join course.students student "
			+ "where student.id = :id")
	List<Course> findAllStudentCourses(@Param("id")int id);
}
