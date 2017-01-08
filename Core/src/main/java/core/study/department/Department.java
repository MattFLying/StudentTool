package core.study.department;

import core.model.base.study.Study;

public class Department extends Study<Department, DepartmentDetails> implements IDepartment {
	
	
	public Department() {
		super(new DepartmentDetails());
	}
	public Department(DepartmentDetails details) {
		super(details);
	}
	
	
	public Department getModel() {
		return this;
	}
	public void setModel(Department model) {
		setDetails(model.getDetails());
	}
}