package core.study.group;

import core.study.details.StudyDetails;
import core.study.fieldofstudy.FieldOfStudy;

public class GroupDetails extends StudyDetails {
	private String groupName, description, year;
	private FieldOfStudy fieldOfStudy;
	
	
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}