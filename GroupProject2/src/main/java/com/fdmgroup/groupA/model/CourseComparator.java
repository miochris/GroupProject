package com.fdmgroup.groupA.model;

import java.util.Comparator;

public class CourseComparator implements Comparator<Course> {

	@Override
	public int compare(Course o1, Course o2) {
		Integer x1 = o1.getStudentList().size();
		Integer x2 = o2.getStudentList().size();
		return x1.compareTo(x2)*-1;
	}

	
	
}
