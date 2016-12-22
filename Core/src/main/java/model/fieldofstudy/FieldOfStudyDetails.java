package model.fieldofstudy;

import model.data.details.Details;
import model.department.Department;

public class FieldOfStudyDetails extends Details {
	private String fieldOfStudyName;
	private Department department;
	
	
	public FieldOfStudyDetails() {
		this.fieldOfStudyName = null;
		this.department = null;
	}
	
	
	public String getFieldOfStudyName() {
		return fieldOfStudyName;
	}
	public void setFieldOfStudyName(String fieldOfStudyName) {
		this.fieldOfStudyName = fieldOfStudyName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}