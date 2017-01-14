package core.study.department;

import core.study.details.StudyDetails;

public class DepartmentDetails extends StudyDetails {
	private String departmentShortName, departmentFullName, departmentBuilding;
	
	
	public DepartmentDetails() {
		super();
	}
	
	
	@Override
	protected void initialize() {
		this.departmentShortName = null;
		this.departmentFullName = null;
		this.departmentBuilding = null;
	}
	public String getDepartmentBuilding() {
		return departmentBuilding;
	}
	public void setDepartmentBuilding(String departmentBuilding) {
		this.departmentBuilding = departmentBuilding;
	}
	public String getDepartmentFullName() {
		return departmentFullName;
	}
	public void setDepartmentFullName(String departmentFullName) {
		this.departmentFullName = departmentFullName.replaceAll(",", "");
	}
	public String getDepartmentShortName() {
		return departmentShortName;
	}
	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}
}