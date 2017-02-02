package core.study.group;

import core.model.base.study.Study;

/***
 * Class represents group.
 * 
 * @author Mateusz Mucha
 *
 */
public class Group extends Study<Group, GroupDetails> implements IGroup {

	/***
	 * Default constructor sets basic fields.
	 */
	public Group() {
		super(new GroupDetails());
	}

	/***
	 * Default constructor sets basic field group details.
	 * 
	 * @param details
	 *            - group details
	 */
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