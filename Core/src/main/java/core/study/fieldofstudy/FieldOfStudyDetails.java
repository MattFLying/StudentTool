package core.study.fieldofstudy;

import core.study.department.Department;
import core.study.details.StudyDetails;

public class FieldOfStudyDetails extends StudyDetails {
	private String fieldOfStudyName;
	private Department department;
	
	
	public FieldOfStudyDetails() {
		super();
	}
	
	
	@Override
	protected void initialize() {
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