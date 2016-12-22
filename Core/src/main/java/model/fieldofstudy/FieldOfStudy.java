package model.fieldofstudy;

public class FieldOfStudy implements IFieldOfStudy {
	private FieldOfStudyDetails fieldOfStudyDetails;
	
	
	public FieldOfStudy() {
		this.fieldOfStudyDetails = new FieldOfStudyDetails();
	}


	public FieldOfStudyDetails getFieldOfStudyDetails() {
		return fieldOfStudyDetails;
	}
	public void setFieldOfStudyDetails(FieldOfStudyDetails details) {
		this.fieldOfStudyDetails = details;
	}
}