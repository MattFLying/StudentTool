package model.group;

import model.data.details.Details;
import model.fieldofstudy.FieldOfStudy;

public class GroupDetails extends Details {
	private String groupName, description;
	private FieldOfStudy fieldOfStudy;
	
	
	public GroupDetails() {
		this.groupName = null;
		this.description = null;
		this.fieldOfStudy = null;
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
}