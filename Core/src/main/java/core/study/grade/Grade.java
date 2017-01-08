package core.study.grade;

import core.model.base.study.Study;

public class Grade extends Study<Grade, GradeDetails> implements IGrade {
	
	
	public Grade() {
		super(new GradeDetails());
	}
	public Grade(GradeDetails details) {
		super(details);
	}
	
	
	public Grade getModel() {
		return this;
	}
	public void setModel(Grade model) {
		setDetails(model.getDetails());
	}
}