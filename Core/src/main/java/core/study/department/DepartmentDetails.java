package core.study.department;

import core.study.details.StudyDetails;

/***
 * Class represents details of department.
 * 
 * @author Mateusz Mucha
 *
 */
public class DepartmentDetails extends StudyDetails {
	private String departmentShortName, departmentFullName, departmentBuilding;

	/***
	 * Default constructor sets basic fields.
	 */
	public DepartmentDetails() {
		super();
	}

	@Override
	protected void initialize() {
		this.departmentShortName = null;
		this.departmentFullName = null;
		this.departmentBuilding = null;
	}

	/***
	 * Method to gets department building.
	 * 
	 * @return department building
	 */
	public String getDepartmentBuilding() {
		return departmentBuilding;
	}

	/***
	 * Method to sets department building.
	 * 
	 * @param departmentBuilding
	 *            - department building
	 */
	public void setDepartmentBuilding(String departmentBuilding) {
		this.departmentBuilding = departmentBuilding;
	}

	/***
	 * Method to gets department full name.
	 * 
	 * @return department full name
	 */
	public String getDepartmentFullName() {
		return departmentFullName;
	}

	/***
	 * Method to sets department full name.
	 * 
	 * @param departmentFullName
	 *            - department full name
	 */
	public void setDepartmentFullName(String departmentFullName) {
		this.departmentFullName = departmentFullName.replaceAll(",", "");
	}

	/***
	 * Method to gets department shortcut name.
	 * 
	 * @return shortcut name
	 */
	public String getDepartmentShortName() {
		return departmentShortName;
	}

	/***
	 * Method to sets department shortcut name.
	 * 
	 * @param departmentShortName
	 *            - shortcut name
	 */
	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}
}