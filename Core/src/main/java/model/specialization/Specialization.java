package model.specialization;

import model.fieldofstudy.FieldOfStudy;

public class Specialization {
	private String specializationName;
	private FieldOfStudy fieldOfStudy;
	
	
	public Specialization() {
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