package model.human.teacher;

import model.data.details.Details;

public class TeacherDetails extends Details {
	private String room, website;
	
	
	public TeacherDetails() {
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