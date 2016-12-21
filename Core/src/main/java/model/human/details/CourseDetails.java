package model.human.details;

public class CourseDetails extends Details {
	private String name;
	private Integer ects, term;
	
	
	public CourseDetails() {
		this.name = null;
		this.ects = null;
		this.term = null;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEcts() {
		return ects;
	}
	public void setEcts(Integer ects) {
		this.ects = ects;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
}