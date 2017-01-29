package core.study.fieldofstudy;

import core.study.department.Department;
import core.study.details.StudyDetails;

/***
 * Class represents field of study details.
 * 
 * @author Mateusz Mucha
 *
 */
public class FieldOfStudyDetails extends StudyDetails {
	private String fieldOfStudyName;
	private Department department;

	/***
	 * Default constructor sets basic fields.
	 */
	public FieldOfStudyDetails() {
		super();
	}

	@Override
	protected void initialize() {
		this.fieldOfStudyName = null;
		this.department = new Department();
	}

	/***
	 * Method to gets field of study name.
	 * 
	 * @return field of study name
	 */
	public String getFieldOfStudyName() {
		return fieldOfStudyName;
	}

	/***
	 * Method to sets field of study name.
	 * 
	 * @param fieldOfStudyName
	 *            - field of study name
	 */
	public void setFieldOfStudyName(String fieldOfStudyName) {
		this.fieldOfStudyName = fieldOfStudyName.replaceAll(",", "");
	}

	/***
	 * Method to gets department of field of study.
	 * 
	 * @return department of field of study
	 */
	public Department getDepartment() {
		return department;
	}

	/***
	 * Method to sets department of field of study.
	 * 
	 * @param department
	 *            - department of field of study
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
}