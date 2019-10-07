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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="university")
public class University {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="country")
	private String country;
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="university",
			cascade=CascadeType.ALL)
	private List<Department> departments;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	@JsonManagedReference
	private User user;
	
	public University() {
		
	}

	
	public University(String name, String country) {
		super();
		this.name = name;
		this.country = country;
	}


	public University(String name, String country, List<Department> departments) {
		super();
		this.name = name;
		this.country = country;
		this.departments = departments;
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


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "University [id=" + id + ", name=" + name + 
				", country=" + country + ", departments=" +
				departments + ", user=" + user + "]";
	}
	
	public void addDepartment(Department theDepartment) {
		if (departments == null) {
			departments = new ArrayList<>();
		}
		departments.add(theDepartment);
	}
	
}
