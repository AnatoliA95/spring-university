package com.university.springuniversitydemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="department")
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="budget")
	private int budget;
	
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="department",
			cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Student> students;
	
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="department",
			cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Teacher> teachers;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, 
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="university_id")
	private University university;
	
	public Department() {	
	}

	public Department(String name, int budget) {
		super();
		this.name = name;
		this.budget = budget;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public void addStudent(Student pmtStudent) {
		if(students == null) {
			students = new ArrayList<>();
		}
		students.add(pmtStudent);
	}
	
	public void addTeacher(Teacher pmtTeacher) {
		if(teachers == null) {
			teachers = new ArrayList<>();
		}
		teachers.add(pmtTeacher);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" +
				name + ", budget=" + budget + ", students=" +
				students + ", teachers=" + teachers + "]";
	}
	
}
