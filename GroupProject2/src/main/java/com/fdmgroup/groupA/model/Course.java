package com.fdmgroup.groupA.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

@Entity
@Table(name="Course")
public class Course {
	
	@GeneratedValue
	@Id
	private int id;
	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column
	private Date endDate;
	@Column
	double price;
	@Column
	private String courseName;
	@OneToMany(mappedBy="course", targetEntity=Student.class, fetch = FetchType.EAGER)
	@Where(clause="DType='S'")
	private List<Student> studentList;
	
	@OneToMany(mappedBy="course", targetEntity=Tutor.class, fetch = FetchType.EAGER)
	@Where(clause="DType='T'")
	private List<Tutor> tutorList;
	
	
	public Course() {
		super();
	}
	
	public Course(Date startDate, Date endDate, String courseName, double price) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseName = courseName;
		this.price = price;
		this.studentList = new ArrayList<Student>();
		this.tutorList = new ArrayList<Tutor>();
	}

	public Course(double price, String courseName) {
		super();
		this.price = price;
		this.courseName = courseName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Tutor> getTutorList() {
		return tutorList;
	}

	public void setTutorList(List<Tutor> tutorList) {
		this.tutorList = tutorList;
	}

}
