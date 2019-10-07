package com.university.springuniversitydemo.service;

import java.util.HashSet;
import java.util.List;

import com.university.springuniversitydemo.entity.Course;
import com.university.springuniversitydemo.entity.Department;
import com.university.springuniversitydemo.entity.Student;
import com.university.springuniversitydemo.entity.Teacher;
import com.university.springuniversitydemo.entity.University;
import com.university.springuniversitydemo.entity.User;

public interface UniversityService {
	public University getUniversity(int id);
	
	public List<Department> getDepartments();
	public void saveDepartment(Department theDepartment);
	
	public Student getStudent(User user);
	public void saveStudent(Student theStudent);
	public List<Student> getStudents();
	public void deleteStudent(int theId);
	
	public List<Teacher> getTeachers();
	public Teacher getTeacher(User theUser);
	public void deleteTeacher(int theId);
	
	public List<Course> getCourses();
	public List<Course> getStudentCourses(int id);
	public Course getCourse(int courseId);
	public HashSet<Course> getTeacherCourses(int id);
	public void saveCourse(Course theCourse);
	public void deleteCourse(int theId);

}
