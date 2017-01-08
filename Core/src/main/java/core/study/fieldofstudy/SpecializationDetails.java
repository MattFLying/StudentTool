package core.study.fieldofstudy;

import core.study.details.StudyDetails;

public class SpecializationDetails extends StudyDetails {
	private String specializationName;
	private FieldOfStudy fieldOfStudy;
	
	
	public SpecializationDetails() {
		super();
	}
	
	
	@Override
	protected void initialize() {
		this.specializationName = null;
		this.fieldOfStudy = null;
	}
	public String getSpecializationName() {
		return specializationName;
	}
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
}