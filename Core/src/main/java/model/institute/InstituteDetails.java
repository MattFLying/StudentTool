package model.institute;

import model.data.details.Details;
import model.department.Department;

public class InstituteDetails extends Details {
	private String instituteShortName, instituteFullName;
	private Department department;
	
	
	public InstituteDetails() {
		this.instituteShortName = null;
		this.instituteFullName = null;
		this.department = null;
	}
	
	
	public String getInstituteFullName() {
		return instituteFullName;
	}
	public void setInstituteFullName(String instituteFullName) {
		this.instituteFullName = instituteFullName;
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