package com.university.springuniversitydemo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.springuniversitydemo.dao.CourseRepository;
import com.university.springuniversitydemo.dao.DepartmentRepository;
import com.university.springuniversitydemo.dao.StudentRepository;
import com.university.springuniversitydemo.dao.TeacherRepository;
import com.university.springuniversitydemo.dao.UniversityRepository;
import com.university.springuniversitydemo.entity.Course;
import com.university.springuniversitydemo.entity.Department;
import com.university.springuniversitydemo.entity.Student;
import com.university.springuniversitydemo.entity.Teacher;
import com.university.springuniversitydemo.entity.University;
import com.university.springuniversitydemo.entity.User;

@Service
public class UniversityServiceImpl implements UniversityService {
	private UniversityRepository universityRepository;
	private TeacherRepository teacherRepository;
	private CourseRepository courseRepository;
	private DepartmentRepository departmentRepository;
	private StudentRepository studentRepository;
	
	@Autowired
	public UniversityServiceImpl(UniversityRepository universityRepository,
			TeacherRepository teacherRepository, 
			CourseRepository courseRepository,
			DepartmentRepository departmentRepository,
			StudentRepository studentRepository) {
		this.universityRepository=universityRepository;
		this.teacherRepository=teacherRepository;
		this.courseRepository=courseRepository;
		this.departmentRepository=departmentRepository;
		this.studentRepository=studentRepository;
	}

	
	@Transactional
	public University getUniversity(int id) {
		Optional<University> result = universityRepository.findById(id);
		University theUniversity = null;
		if (result.isPresent()) {
			theUniversity=result.get();
		} else {
			throw new RuntimeException();
		}
		return theUniversity;
	}

	@Transactional
	public List<Teacher> getTeachers() {
		return teacherRepository.findAllByOrderByLastName();
	}


	@Transactional
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}


	@Transactional
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}


	@Transactional
	public Student getStudent(User user) {
		return studentRepository.findByUser(user);
	}


	@Transactional
	public List<Course> getStudentCourses(int id) {
		return courseRepository.findAllStudentCourses(id);
	}


	@Override
	public Course getCourse(int courseId) {
		Optional<Course> result = courseRepository.findById(courseId);
		Course theCourse = null;
		if (result.isPresent()) {
			theCourse=result.get();
		} else {
			throw new RuntimeException();
		}
		return theCourse;
	}


	@Transactional		
	public Teacher getTeacher(User theUser) {
		return teacherRepository.findByUser(theUser);
	}


	@Transactional
	public HashSet<Course> getTeacherCourses(int id) {
		return teacherRepository.getTeacherCourses(id);
	}


	@Transactional
	public void saveCourse(Course theCourse) {
		courseRepository.save(theCourse);
	}


	@Transactional
	public void deleteCourse(int theId) {
		courseRepository.deleteById(theId);
	}


	@Transactional
	public void saveStudent(Student theStudent) {
		studentRepository.save(theStudent);
		
	}


	@Transactional
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}


	@Transactional
	public void deleteTeacher(int theId) {
		teacherRepository.deleteById(theId);
		
	}


	@Transactional
	public void deleteStudent(int theId) {
		studentRepository.deleteById(theId);
		
	}


	@Transactional
	public void saveDepartment(Department theDepartment) {
		departmentRepository.save(theDepartment);
		
	}




	

}
