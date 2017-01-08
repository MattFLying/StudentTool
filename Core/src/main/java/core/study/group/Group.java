package core.study.group;

import core.model.base.study.Study;

public class Group extends Study<Group, GroupDetails> implements IGroup {
	
	
	public Group() {
		super(new GroupDetails());
	}
	public Group(GroupDetails details) {
		super(details);
	}
	
	
	public Group getModel() {
		return this;
	}
	public void setModel(Group model) {
		setDetails(model.getDetails());
	}
}