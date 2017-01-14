package core.study.department;

import core.study.details.StudyDetails;

public class InstituteDetails extends StudyDetails {
	private String instituteShortName, instituteFullName;
	private Department department;
	
	
	public InstituteDetails() {
		super();
	}
	

	@Override
	protected void initialize() {
		this.instituteShortName = null;
		this.instituteFullName = null;
		this.department = new Department();
	}
	public String getInstituteFullName() {
		return instituteFullName;
	}
	public void setInstituteFullName(String instituteFullName) {
		this.instituteFullName = instituteFullName.replaceAll(",", "");
	}
	public String getInstituteShortName() {
		return instituteShortName;
	}
	public void setInstituteShortName(String instituteShortName) {
		this.instituteShortName = instituteShortName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}