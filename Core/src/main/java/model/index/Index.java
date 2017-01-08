package model.index;

import java.util.ArrayList;
import java.util.List;

import core.humanity.student.Student;

public class Index {
	private Student student;
	private List<Semester> semesterList;
	
	
	public Index(Student student) {
		this.student = student;
		this.setSemesterList(new ArrayList<Semester>());
	}
	
	
	public List<Semester> getSemesterList() {
		return semesterList;
	}
	public void setSemesterList(List<Semester> semesterList) {
		this.semesterList = semesterList;
	}
	public Semester getSemester(Integer semesterNumber) {
		return semesterList
				.stream()
				.filter(semester -> semester.getSemesterNumber() == semesterNumber)
				.findFirst()
				.get();
	}
	public Semester getCurrentSemester() {
		return semesterList
				.stream()
				.filter(semester -> semester.getSemesterNumber() == student.getDetails().getCurrentTermNumber())
				.findFirst()
				.get();
	}
	public void addSemester(Semester semester) {
		this.semesterList.add(semester);
	}
}