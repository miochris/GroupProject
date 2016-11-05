package com.fdmgroup.groupA.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lesson {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name="tutor")
	private Tutor tutor;
	@OneToMany(mappedBy="lesson",cascade = CascadeType.ALL)
	private List<Grade> gradeList;

	public Lesson() {
	}

	public Lesson(Tutor tutor, String name) {
		this.tutor = tutor;
		this.name = name;
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

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

}
