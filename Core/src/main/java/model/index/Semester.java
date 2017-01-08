package model.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.study.course.Course;
import core.study.grade.Grade;

public class Semester {
	private Integer semesterNumber;
	private HashMap<Course, List<Grade>> grades;
	
	
	public Semester() {
		initialize(0, new HashMap<Course, List<Grade>>());
	}
	public Semester(Integer semesterNumber) {
		initialize(semesterNumber, new HashMap<Course, List<Grade>>());
	}
	public Semester(Integer semesterNumber, HashMap<Course, List<Grade>> grades) {
		initialize(semesterNumber, grades);
	}
	
	
	private void initialize(Integer semesterNumber, HashMap<Course, List<Grade>> grades) {
		this.semesterNumber = 0;
		this.grades = grades;
	}
	public Integer getSemesterNumber() {
		return semesterNumber;
	}
	public void setSemesterNumber(Integer semesterNumber) {
		this.semesterNumber = semesterNumber;
	}
	public HashMap<Course, List<Grade>> getGrades() {
		return grades;
	}
	public void setGrades(HashMap<Course, List<Grade>> grades) {
		this.grades = grades;
	}
	public void putGradesForCourse(Course course, List<Grade> grades) {
		this.grades.put(course, grades);
	}
	public List<Course> getSemesterCourses() {
		List<Course> courses = new ArrayList<Course>();
		
		for(Map.Entry entry : this.grades.entrySet()) {
			courses.add((Course)entry.getKey());
		}
		
		return courses;
	}
	public List<Grade> getGradesForCourse(Course course) {
		List<Grade> list = new ArrayList<Grade>();
		
		list.addAll(this.grades.get(course));
		
		return list;
	}
}