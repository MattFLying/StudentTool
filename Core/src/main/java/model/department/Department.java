package model.department;

public class Department implements IDepartment {
	private DepartmentDetails departmentDetails;
	
	
	public Department() {
		this.departmentDetails = new DepartmentDetails();
	}
	
	
	public DepartmentDetails getDepartmentDetails() {
		return departmentDetails;
	}
	public void setDepartmentDetails(DepartmentDetails details) {
		this.departmentDetails = details;
	}
}