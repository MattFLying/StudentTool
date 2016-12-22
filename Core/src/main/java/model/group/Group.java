package model.group;

public class Group implements IGroup {
	private GroupDetails groupDetails;
	
	
	public Group() {
		this.groupDetails = new GroupDetails();
	}
	
	
	public GroupDetails getGroupDetails() {
		return groupDetails;
	}
	public void setGroupDetails(GroupDetails details) {
		this.groupDetails = details;
	}
}