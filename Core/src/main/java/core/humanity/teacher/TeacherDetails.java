package core.humanity.teacher;

import core.humanity.details.PersonalDetails;

public class TeacherDetails extends PersonalDetails {
	private String room, website;
	
	
	public TeacherDetails() {
		super();
	}
	
	
	@Override
	protected void initialize() {
		super.initialize();
		this.room = null;
		this.website = null;
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
}