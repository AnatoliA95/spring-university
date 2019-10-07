package com.university.springuniversitydemo.dao;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.university.springuniversitydemo.entity.Course;
import com.university.springuniversitydemo.entity.Teacher;
import com.university.springuniversitydemo.entity.User;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	List<Teacher> findAllByOrderByLastName();
	Teacher findByUser(User theUser);
	
	@Query("SELECT course FROM Course course WHERE teacher.id = :id")
	HashSet<Course> getTeacherCourses(@Param("id")int id);

}
