package core.study.department;

import core.model.base.study.Study;

/***
 * Class represents department.
 * 
 * @author Mateusz Mucha
 *
 */
public class Department extends Study<Department, DepartmentDetails> implements IDepartment {

	/***
	 * Default constructor sets basic fields.
	 */
	public Department() {
		super(new DepartmentDetails());
	}

	/***
	 * Default constructor sets basic field department details.
	 */
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