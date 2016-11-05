package com.fdmgroup.groupA.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("S")
public class Student extends Users {
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Grade> gradeList;
	@Column
	private double mark;
	
	public Student() {
		super();
	}

	public Student(String firstname, String lastname, String email, String username, String password, double balance, String role) {
		super(firstname, lastname, email, username, password, balance, role);
		this.gradeList = new ArrayList<Grade>();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	

	
	


}
