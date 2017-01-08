package core.humanity.student;

import core.model.base.humanity.Person;
import model.index.Index;

public class Student extends Person<Student, StudentDetails> implements IStudent {
	private Index index;
	
	
	public Student() {
		super(new StudentDetails());
		this.setIndex(new Index(this));
	}
	public Student(StudentDetails details) {
		super(details);
		this.setIndex(new Index(this));
	}
	
	
	public Student getModel() {
		return this;
	}
	public void setModel(Student model) {
		setDetails(model.getDetails());
	}
	public Index getIndex() {
		return index;
	}
	public void setIndex(Index index) {
		this.index = index;
	}
}