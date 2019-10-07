package com.university.springuniversitydemo.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.university.springuniversitydemo.entity.Course;
import com.university.springuniversitydemo.entity.Department;
import com.university.springuniversitydemo.entity.Student;
import com.university.springuniversitydemo.entity.Teacher;
import com.university.springuniversitydemo.entity.University;
import com.university.springuniversitydemo.service.UniversityService;
import com.university.springuniversitydemo.service.UserService;

@Controller
public class DemoController {
	@Autowired
	private UniversityService universityService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/departments")
	public String showDepartments(Model theModel) {
		List<Department> theDepartments = universityService.getDepartments();
		theModel.addAttribute("departments", theDepartments);
		return "departments";
	}

	@GetMapping("/courses")
	public String showCourses(Model theModel) {
		//take user
		User user = (User) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		com.university.springuniversitydemo.entity.User theUser =
				userService.findByUserName(user.getUsername());
		
		//find student by user and take courses
		Student theStudent = universityService.getStudent(theUser);
		if(theStudent != null) {
			int id = theStudent.getId();
			List<Course> theCourses = universityService.getCourses();
			List<Course> courses = universityService.getStudentCourses(id);
			for(int i = 0; i < courses.size(); i++) {
				if(theCourses.contains(courses.get(i))) {
					theCourses.remove(courses.get(i));
				}
			}
					theModel.addAttribute("student", theStudent);
					theModel.addAttribute("courses", theCourses);
					theModel.addAttribute("myCourses", courses);
		}
		//find teacher by user and take courses
		Teacher theTeacher = universityService.getTeacher(theUser);
		if(theTeacher != null) {
			List<Course> theCourses = universityService.getCourses();
			int id = theTeacher.getId();
			HashSet<Course> myCourses = universityService.getTeacherCourses(id);
			theModel.addAttribute("courses", theCourses);
			theModel.addAttribute("myCourses", myCourses);
		}
		return "courses";
	}
	
	//register student to course
	@GetMapping("/registerToCourse")
	public String registerToCourse(
			@RequestParam("courseId") int courseId,
			Model theModel) {
		User user = (User) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		com.university.springuniversitydemo.entity.User theUser =
				userService.findByUserName(user.getUsername());
		Student theStudent = universityService.getStudent(theUser);
		Course registration = universityService.getCourse(courseId);
		theStudent.addCourse(registration);
		int id = theStudent.getId();
		List<Course> theCourses = universityService.getCourses();
		List<Course> courses = universityService.getStudentCourses(id);
		for(int i = 0; i < courses.size(); i++) {
			if(theCourses.contains(courses.get(i))) {
				theCourses.remove(courses.get(i));
			}
		}
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("courses", theCourses);
		theModel.addAttribute("myCourses", courses);
		return "courses";
	}

	//unregister the student from course
	@GetMapping("/unregisterFromCourse")
	public String unregisterFromCourse(@RequestParam("courseId")int courseId) {
		User user = (User) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		com.university.springuniversitydemo.entity.User theUser =
				userService.findByUserName(user.getUsername());
		Student theStudent = universityService.getStudent(theUser);
		Course theCourse = universityService.getCourse(courseId);
		theStudent.getCourses().remove(theCourse);
		universityService.saveStudent(theStudent);
		return "redirect:/courses";
	}
	
	//get teachers from db
	@GetMapping("/teachers")
	public String showTeachers(Model theModel) {
		List<Teacher> theTeachers = universityService.getTeachers();
		theModel.addAttribute("teachers", theTeachers);
		return "teachers";
	}
	
	//get students from db
	@GetMapping("/students")
	public String showStudents(Model theModel) {
		List<Student> theStudents = universityService.getStudents();
		theModel.addAttribute("students", theStudents);
		return "students";
	}
	
	//add department
	@GetMapping("/showFormForAddDepartment")
	public String showFormForAddDepartment(Model theModel) {
		Department theDepartment = new Department();
		theModel.addAttribute("department", theDepartment);
		return"department-form";
	}
	
	//save department
	@PostMapping("/saveDepartment")
	public String saveDepartment(@ModelAttribute("department") Department theDepartment) {
		University theUniversity = universityService.getUniversity(1);
		theDepartment.setUniversity(theUniversity);
		universityService.saveDepartment(theDepartment);
		return "redirect:/departments";
	}
	
	//add course for temporary teacher
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Course theCourse = new Course();
		theModel.addAttribute("course", theCourse);
		return "course-form";
	}
	
	//save course
	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course theCourse){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		com.university.springuniversitydemo.entity.User theUser = userService.findByUserName(user.getUsername());
		Teacher theTeacher = universityService.getTeacher(theUser);
		theCourse.setTeacher(theTeacher);
		universityService.saveCourse(theCourse);
		return "redirect:/courses";
	}
	
	//delete course
	@GetMapping("/deleteCourse")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		universityService.deleteCourse(theId);
		return "redirect:/courses";
	}
	
	//delete teacher 
	@GetMapping("/deleteTeacher")
	public String deleteTeacher(@RequestParam("teacherId") int theId) {
		universityService.deleteTeacher(theId);
		return "redirect:/teachers";
	}
	
	//delete student
	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("studentId") int theId) {
		universityService.deleteStudent(theId);
		return "redirect:/students";
	}
}












