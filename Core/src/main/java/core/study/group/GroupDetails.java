package core.study.group;

import core.study.details.StudyDetails;
import core.study.fieldofstudy.FieldOfStudy;

/***
 * Class represents group details.
 * 
 * @author Mateusz Mucha
 *
 */
public class GroupDetails extends StudyDetails {
	private String groupName, description, year;
	private FieldOfStudy fieldOfStudy;

	/***
	 * Default constructor sets basic fields.
	 */
	public GroupDetails() {
		super();
	}

	@Override
	protected void initialize() {
		this.groupName = null;
		this.description = null;
		this.fieldOfStudy = null;
		this.year = null;
	}

	/***
	 * Method to gets group name.
	 * 
	 * @return group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/***
	 * Method to sets group name.
	 * 
	 * @param groupName
	 *            - group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/***
	 * Method to gets group description.
	 * 
	 * @return group description
	 */
	public String getDescription() {
		return description;
	}

	/***
	 * Method to sets group description.
	 * 
	 * @param description
	 *            - group description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/***
	 * Method to gets field of study for group.
	 * 
	 * @return field of study for group
	 */
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}

	/***
	 * Method to sets field of study for group.
	 * 
	 * @param fieldOfStudy
	 *            - field of study for group
	 */
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	/***
	 * Method to gets group exist year.
	 * 
	 * @return group exist year
	 */
	public String getYear() {
		return year;
	}

	/***
	 * Method to sets group exist year.
	 * 
	 * @param year
	 *            - group exist year
	 */
	public void setYear(String year) {
		this.year = year;
	}
}