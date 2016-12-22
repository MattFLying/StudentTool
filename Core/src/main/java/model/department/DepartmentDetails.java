package model.department;

import model.data.details.Details;

public class DepartmentDetails extends Details {
	private String departmentShortName, departmentFullName, departmentBuilding;
	
	
	public DepartmentDetails() {
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
		this.departmentFullName = departmentFullName;
	}
	public String getDepartmentShortName() {
		return departmentShortName;
	}
	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}
}