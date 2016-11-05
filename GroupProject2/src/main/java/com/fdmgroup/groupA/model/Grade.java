
package com.fdmgroup.groupA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Grade")
public class Grade {

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private int id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Student")
	private Student student;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="lesson")
	private Lesson lesson;

	@Column
	private int mark;

	public Grade() {

	}

	public Grade(Student student, Lesson lesson, int mark) {
		super();
		this.student = student;
		this.lesson = lesson;
		this.mark = mark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

}
