package core.study.department;

import core.study.details.StudyDetails;

/***
 * Class represents institute details.
 * 
 * @author Mateusz Mucha
 *
 */
public class InstituteDetails extends StudyDetails {
	private String instituteShortName, instituteFullName;
	private Department department;

	/***
	 * Default constructor sets basic fields.
	 */
	public InstituteDetails() {
		super();
	}

	@Override
	protected void initialize() {
		this.instituteShortName = null;
		this.instituteFullName = null;
		this.department = new Department();
	}

	/***
	 * Method to gets full name of institute.
	 * 
	 * @return full name of institute
	 */
	public String getInstituteFullName() {
		return instituteFullName;
	}

	/***
	 * Method to sets full name of institute.
	 * 
	 * @param instituteFullName
	 *            - full name of institute
	 */
	public void setInstituteFullName(String instituteFullName) {
		this.instituteFullName = instituteFullName.replaceAll(",", "");
	}

	/***
	 * Method to gets shortcut name of institute.
	 * 
	 * @return shortcut name of institute
	 */
	public String getInstituteShortName() {
		return instituteShortName;
	}

	/***
	 * Method to sets shortcut name of institute.
	 * 
	 * @param instituteShortName
	 *            - shortcut name of institute
	 */
	public void setInstituteShortName(String instituteShortName) {
		this.instituteShortName = instituteShortName;
	}

	/***
	 * Method to gets department of institute.
	 * 
	 * @return department of institute
	 */
	public Department getDepartment() {
		return department;
	}

	/***
	 * Method to sets department of institute.
	 * 
	 * @param department
	 *            - department of institute
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
}