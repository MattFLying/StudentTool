package model.grade;

public class Grade implements IGrade {
	private GradeDetails gradeDetails;
	
	
	public Grade() {
		this.gradeDetails = new GradeDetails();;
	}
	public Grade(GradeDetails details) {
		this.gradeDetails = details;
	}
	
	
	public GradeDetails getGradeDetails() {
		return gradeDetails;
	}
	public void setGradeDetails(GradeDetails details) {
		this.gradeDetails = details;
	}
}