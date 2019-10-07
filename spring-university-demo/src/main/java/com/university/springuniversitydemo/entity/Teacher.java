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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="teacher")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, 
						CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="department_id")
	@JsonBackReference
	private Department department;
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="teacher", 
			cascade= CascadeType.ALL)
	@JsonManagedReference
	private List<Course> courses;
	@OneToOne(cascade=CascadeType.ALL)
	@JsonManagedReference
	private User user;
	
	public Teacher() {
		
	}

	public Teacher(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	public Teacher(String firstName, String lastName, String email,  Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", firstName=" + firstName + 
				", lastName=" + lastName + ", email=" + email +
				", department=" + department + ", courses=" + courses +
				", user=" + user + "]";
	}

	public void addCourse(Course tempCourse) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(tempCourse);
	}
	
}
