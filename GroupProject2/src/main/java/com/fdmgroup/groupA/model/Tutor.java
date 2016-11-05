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

import org.hibernate.annotations.Type;

@Entity
@DiscriminatorValue("T")
public class Tutor extends Users {

	@OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Lesson> lessonList;
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;

	@Column
	@Type(type="yes_no")
	private Boolean hired;
	
	@Column
	private double percentage;
	
	public Tutor() {

	}

	public Tutor(String firstname, String lastname, String email, String username, String password, double balance, String role) {
		super(firstname, lastname, email, username, password, balance, role);
		this.lessonList = new ArrayList<Lesson>();
		this.hired = false;
		this.percentage = 10.0;
	}

	public List<Lesson> getLessonList() {
		return lessonList;
	}

	public void setLesson(List<Lesson> lessonList) {
		this.lessonList = lessonList;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean isHired() {
		return hired;
	}

	public void setHired(boolean hired) {
		this.hired = hired;
	}

	public Course getCourse() {
		return course;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	
}
