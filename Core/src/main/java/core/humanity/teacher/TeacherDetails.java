package core.humanity.teacher;

import core.humanity.details.PersonalDetails;
import core.study.department.Department;
import core.study.department.Institute;

/***
 * Class represents teacher.
 * 
 * @author Mateusz Mucha
 *
 */
public class TeacherDetails extends PersonalDetails {
	private String room, website;
	private Institute institute;
	private Department department;

	/***
	 * Default constructor sets basic fields.
	 */
	public TeacherDetails() {
		super();
	}

	@Override
	protected void initialize() {
		super.initialize();
		this.room = null;
		this.website = null;
		this.institute = new Institute();
		this.department = new Department();
	}

	/***
	 * Method to get room of teacher.
	 * 
	 * @return room of teacher
	 */
	public String getRoom() {
		return room;
	}

	/***
	 * Method to set room of teacher.
	 * 
	 * @param room
	 *            - room of teacher
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/***
	 * Method to gets web site of teacher.
	 * 
	 * @return web site of teacher
	 */
	public String getWebsite() {
		return website;
	}

	/***
	 * Method to sets web site of teacher.
	 * 
	 * @param website
	 *            - web site of teacher
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/***
	 * Method to gets institute of teacher.
	 * 
	 * @return institute of teacher
	 */
	public Institute getInstitute() {
		return institute;
	}

	/***
	 * Method to sets institute of teacher.
	 * 
	 * @param institute
	 *            - institute of teacher
	 */
	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	/***
	 * Method to gets department of teacher.
	 * 
	 * @return department of teacher
	 */
	public Department getDepartment() {
		return department;
	}

	/***
	 * Method to gets department of teacher.
	 * 
	 * @param department
	 *            - department of teacher
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
}