package com.fdmgroup.groupA.model;

import java.util.Comparator;

public class LessonComparator implements Comparator<Lesson>{

	@Override
	public int compare(Lesson o1, Lesson o2) {
		String x1 = o1.getName();
		String x2 = o2.getName();
		return x1.compareTo(x2);
	}

}
