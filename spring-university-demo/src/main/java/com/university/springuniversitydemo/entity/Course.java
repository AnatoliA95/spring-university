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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="topic")
	private String topic;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, 
						CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="teacher_id")
	@JsonBackReference
	private Teacher teacher;
	
	@ManyToMany(fetch=FetchType.LAZY, 
			cascade= {CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="student_course",
			joinColumns = @JoinColumn(name="course_id"),
			inverseJoinColumns = @JoinColumn(name="student_id")
			)
	@JsonBackReference
	private List<Student> students;
	
	public Course() {
		
	}

	public Course(String topic, Teacher teacher) {
		super();
		this.topic = topic;
		this.teacher = teacher;
	}

	public Course(String topic) {
		super();
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", topic=" + 
				topic + ", teacher=" + teacher + 
				", students=" + students + "]";
	}

	public void addStudent(Student pmtStudent) {
		if(students == null) {
			students = new ArrayList<>();
		}
		students.add(pmtStudent);
	}

	
}
