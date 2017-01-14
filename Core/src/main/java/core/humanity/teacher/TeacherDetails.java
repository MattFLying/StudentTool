package core.humanity.teacher;

import core.humanity.details.PersonalDetails;
import core.study.department.Department;
import core.study.department.Institute;

public class TeacherDetails extends PersonalDetails {
	private String room, website;
	private Institute institute;
	private Department department;
	
	
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
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Institute getInstitute() {
		return institute;
	}
	public void setInstitute(Institute institute) {
		this.institute = institute;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}